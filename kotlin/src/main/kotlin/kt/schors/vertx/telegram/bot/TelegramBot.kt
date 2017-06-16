/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017 schors
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package kt.schors.vertx.telegram.bot

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import io.vertx.core.Vertx
import io.vertx.core.http.HttpClient
import io.vertx.core.http.HttpClientOptions
import kt.schors.vertx.telegram.bot.api.methods.TelegramMethod
import kt.schors.vertx.telegram.bot.util.NOKResponseException
import org.apache.log4j.Logger
import java.io.IOException


class TelegramBot(val vertx: Vertx, val options: TelegramOptions, receiver: UpdateReceiver? = null) {
    val log: Logger = Logger.getLogger("TelegramBot")

    val facilities = mutableMapOf<String, Any>()
    val mapper = jacksonObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    }
    val httpClient: HttpClient = vertx.createHttpClient(HttpClientOptions().apply {
        isSsl = true
        isTrustAll = true
        idleTimeout = options.pollingTimeout
        maxPoolSize = options.maxConnections
        defaultHost = "api.telegram.org"
        defaultPort = 443
        logActivity = true
        options.proxyOptions?.let { setProxyOptions(it) }
    })
    var receiver: UpdateReceiver? = receiver?.apply { bot(this@TelegramBot) }

    fun start() {
        receiver?.start()
    }

    fun stop() {
        receiver?.stop()
    }

    inline fun <reified T : Any> send(message: TelegramMethod, handler: Handler<AsyncResult<T>>? = null) {
        val toSend: String? = try {
            mapper.writeValueAsString(message)
        } catch (e: JsonProcessingException) {
            log.error(e, e)
            null
        }

        toSend?.let {
            httpClient
                    .post("/bot${options.botToken}/${message.method}")
                    .handler { response ->
                        response.bodyHandler { event ->
                            handler?.let {
                                val json = event.toJsonObject()
                                when (json.getBoolean("ok")) {
                                    true -> {
                                        val jsonMessage = json.getJsonObject("result")
                                        var resultMessage: T? = try {
                                            mapper.readValue(jsonMessage.toString())
                                        } catch (e: IOException) {
                                            null
                                        }
                                        it.handle(AResult(result = resultMessage))
                                    }
                                    else -> {
                                        log.warn("### Unsuccessful response: $json")
                                        val errorCode = json.getString("error_code")
                                        val errorDescription = json.getString("description")
                                        it.handle(AResult(exception = NOKResponseException(errorCode, errorDescription)))
                                    }
                                }
                            }
                        }
                    }
                    .exceptionHandler { e ->
                        handler?.let { it.handle(AResult(exception = e)) }
                    }
                    .setTimeout(75000)
                    .putHeader("Content-Type", "application/json")
                    .end(toSend, "UTF-8")
        }
    }

    class AResult<T : Any>(val result: T? = null, val exception: Throwable? = null) : AsyncResult<T> {
        override fun result(): T? = result
        override fun cause(): Throwable? = exception
        override fun succeeded(): Boolean = exception == null
        override fun failed(): Boolean = exception != null
    }
}