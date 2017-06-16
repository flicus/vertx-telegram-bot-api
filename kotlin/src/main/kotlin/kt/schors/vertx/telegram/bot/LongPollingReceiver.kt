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
import io.vertx.core.Handler
import io.vertx.core.http.HttpClient
import io.vertx.core.http.HttpClientOptions
import kt.schors.vertx.telegram.bot.api.methods.GetUpdates
import kt.schors.vertx.telegram.bot.api.types.Update
import org.apache.log4j.Logger

class LongPollingReceiver(handler: Handler<Update>? = null, var bot: TelegramBot? = null) : UpdateReceiver {
    private val log: Logger = Logger.getLogger("LongPollingReceiver")

    private val handler: Handler<Update>? = handler
    private val pollHandler: PollHandler = PollHandler()
    private val mapper = jacksonObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
    }
    private var httpClient: HttpClient? = null
    private var taskId: Long = 0
    private var lastReceivedUpdate = 0

    override fun bot(bot: TelegramBot) {
        this.bot = bot
        httpClient = bot.vertx.createHttpClient(HttpClientOptions().apply {
            isSsl = true
            isTrustAll = true
            idleTimeout = bot.options.pollingTimeout
            maxPoolSize = bot.options.maxConnections
            defaultHost = "api.telegram.org"
            defaultPort = 443
            logActivity = true
            bot.options.proxyOptions?.let { setProxyOptions(it) }
        })
    }

    override fun start() {
        runTimer(500)
    }

    override fun stop() {
        bot?.vertx?.cancelTimer(taskId)
    }

    private fun runTimer(timeout: Long) {
        log.debug("Run timer task: $timeout")
        taskId = bot?.vertx?.setTimer(timeout, pollHandler) as Long
    }

    private inner class PollHandler : Handler<Long> {
        override fun handle(event: Long?) {
            log.debug("Poll handler: $event")
            val getUpdates = GetUpdates(offset = lastReceivedUpdate + 1, timeout = 50)
            val toSend = try {
                mapper.writeValueAsString(getUpdates)
            } catch (e: JsonProcessingException) {
                log.error("### Unable to serialize to JSON", e)
                null
            }
            toSend?.let {
                httpClient
                        ?.post("/bot${bot?.options?.botToken}/${getUpdates.method}")
                        ?.handler { response ->
                            log.debug("Response code: ${response.statusCode()}")
                            response.bodyHandler { body ->
                                val json = body.toJsonObject()
                                val ok = json.getBoolean("ok")
                                log.debug("Is ok: $ok")
                                when (ok) {
                                    true -> {
                                        val updates = json.getJsonArray("result")
                                        log.debug("Updates: $updates")
                                        updates.asSequence()
                                                .map {
                                                    try {
                                                        mapper.readValue<Update>(it.toString())
                                                    } catch (e: Exception) {
                                                        log.error(e, e)
                                                        null
                                                    }
                                                }
                                                .filter { it != null && it.updateId > lastReceivedUpdate }
                                                .forEach {
                                                    log.debug("U: $it")
                                                    lastReceivedUpdate = it!!.updateId
                                                    handler?.handle(it)
                                                }
                                    }
                                    else -> {
                                        log.warn("### Unsuccessful response: $json")
                                    }
                                }
                                runTimer(500)
                            }
                        }
                        ?.exceptionHandler { e ->
                            log.error(e, e)
                            runTimer(500)
                        }
                        ?.setTimeout(75000)
                        ?.putHeader("Content-Type", "application/json")
                        ?.end(toSend, "UTF-8")
            }
        }
    }
}