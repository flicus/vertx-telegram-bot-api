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

package org.schors.vertx.telegram.bot;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import org.schors.vertx.telegram.bot.commands.CommandManager;
import org.schors.vertx.telegram.bot.util.MultipartHelper;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot {

    private Vertx vertx;
    private HttpClient client;
    private TelegramOptions botOptions;
    private UpdateReceiver receiver;
    private Map<String, Object> facilities = new HashMap<>();

    public TelegramBot(Vertx vertx, TelegramOptions options) {
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

        if (options.getProxyOptions() != null)
            httpOptions.setProxyOptions(options.getProxyOptions());

        client = vertx.createHttpClient(httpOptions);
    }

    public static TelegramBot create(Vertx vertx) {
        return create(vertx, new TelegramOptions());
    }

    public static TelegramBot create(Vertx vertx, TelegramOptions options) {
        return new TelegramBot(vertx, options);
    }

    public static TelegramBot create(Vertx vertx, TelegramOptions options, CommandManager cm) {
        TelegramBot tb = new TelegramBot(vertx, options);
        cm.setBot(tb);
        return tb;
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

    public Vertx getVertx() {
        return vertx;
    }

    public TelegramOptions getOptions() {
        return botOptions;
    }

    public HttpClient getClient() {
        return client;
    }

    public TelegramBot addFacility(String key, Object facility) {
        facilities.put(key, facility);
        return this;
    }

    public Object getFacility(String key) {
        return facilities.get(key);
    }

    private void send(BotApiMethod message) {
        client
                .post(Constants.BASEURL + getOptions().getBotToken() + "/" + message.getPath())
                .handler(response -> {
                    response.bodyHandler(event -> {
                    });
                })
                .exceptionHandler(e -> {
                })
                .setTimeout(75000)
                .putHeader("Content-Type", "application/json")
                .end(message.toJson().encode(), "UTF-8");
    }

    public void sendMessage(SendMessage message) {
        send(message);
    }

    public void sendChatAction(SendChatAction chatAction) {
        send(chatAction);
    }

    public void sendDocument(SendDocument document) {
        vertx.executeBlocking(future -> {
            HttpClientRequest request = client
                    .post(Constants.BASEURL + getOptions().getBotToken() + "/" + SendDocument.PATH)
                    .handler(response -> {
                        response.bodyHandler(event -> {
                        });
                    })
                    .exceptionHandler(e -> {
                    })
                    .setTimeout(75000)
                    .setChunked(true);
            MultipartHelper multipartHelper = new MultipartHelper(vertx, request);
            multipartHelper
                    .start()
                    .putTextBody(SendDocument.CHATID_FIELD, document.getChatId())
                    .putBinaryBody(SendDocument.DOCUMENT_FIELD, document.getDocument(), "application/octet-stream", document.getDocumentName(), event -> {
                        if (event.succeeded()) {
                            if (document.getReplyMarkup() != null) {
                                multipartHelper.putTextBody(SendDocument.REPLYMARKUP_FIELD, document.getReplyMarkup().toJson().encode());
                            }
                            if (document.getReplyToMessageId() != null) {
                                multipartHelper.putTextBody(SendDocument.REPLYTOMESSAGEID_FIELD, document.getReplyToMessageId().toString());
                            }
                            if (document.getCaption() != null) {
                                multipartHelper.putTextBody(SendDocument.CAPTION_FIELD, document.getCaption());
                            }
                            if (document.getDisableNotification() != null) {
                                multipartHelper.putTextBody(SendDocument.DISABLENOTIFICATION_FIELD, document.getDisableNotification().toString());
                            }
                            multipartHelper.stop();
                        }
                        request.end();
                    });
        }, event -> {

        });
    }

}
