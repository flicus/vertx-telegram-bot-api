/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016  schors
 *
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

package org.schors.vertx.telegram;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class TelegramBot {

    private Vertx vertx;
    private HttpClient client;
    private HttpClient pollClient;
    private TelegramOptions botOptions;
    private UpdateReceiver receiver;

    private TelegramBot(Vertx vertx, TelegramOptions options) {
        this.vertx = vertx;
        this.botOptions = options;

        HttpClientOptions httpOptions = new HttpClientOptions()
//                .setKeepAlive(true)
                .setSsl(true)
//                .setOpenSslEngineOptions(new OpenSSLEngineOptions())
//                .setUseAlpn(true)
                .setTrustAll(true)
                .setIdleTimeout(this.getOptions().getPollingTimeout())
                .setMaxPoolSize(this.getOptions().getMaxConnections())
                .setDefaultHost(Constants.BASEHOST)
                .setDefaultPort(443)
                .setLogActivity(true);

        client = vertx.createHttpClient(httpOptions);
        pollClient = vertx.createHttpClient(httpOptions);
    }

    public static TelegramBot create(Vertx vertx) {
        return create(vertx, new TelegramOptions());
    }

    public static TelegramBot create(Vertx vertx, TelegramOptions options) {
        return new TelegramBot(vertx, options);
    }

    public TelegramBot receiver(UpdateReceiver updateReceiver) {
        this.receiver = updateReceiver;
        updateReceiver.bot(this);
        return this;
    }

    public TelegramBot start() {
        receiver.start();
        return this;
    }

    public TelegramBot stop() {
        receiver.stop();
        return this;
    }

    public void sendMessage(SendMessage message) {
        String toSend = message.toJson().encode();
        System.out.println(toSend);
        client
                .post(Constants.BASEURL + getOptions().getBotToken() + "/" + SendMessage.PATH)
                .handler(response -> {
                    System.out.println(response.statusCode());
                    response.bodyHandler(event -> {
                        System.out.println(event);
                    });
                }).exceptionHandler(e -> {
            System.out.println(e);

        })
                .setTimeout(75000)
                .putHeader("Content-Type", "application/json")
                .end(toSend, "UTF-8");
    }

    public Vertx getVertx() {
        return vertx;
    }

    public TelegramOptions getOptions() {
        return botOptions;
    }

    public HttpClient getClient() {
        return pollClient;
    }
}
