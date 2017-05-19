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

package org.schors.vertx.telegram.bot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import org.apache.log4j.Logger;
import org.schors.vertx.telegram.bot.api.methods.SetWebhook;
import org.schors.vertx.telegram.bot.api.types.Update;
import org.schors.vertx.telegram.bot.commands.CommandHandler;
import org.schors.vertx.telegram.bot.util.Util;

public class WebhookReceiver implements UpdateReceiver {

    private static final Logger log = Logger.getLogger(WebhookReceiver.class);

    private TelegramBot bot;
    private Handler<Update> handler;
    private int lastReceivedUpdate = 0;
    private ObjectMapper mapper = new ObjectMapper();
    private HttpServer server;

    public WebhookReceiver() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static WebhookReceiver create() {
        return new WebhookReceiver();
    }

    @Override
    public UpdateReceiver onUpdate(Handler<Update> handler) {
        this.handler = handler;
        if (handler instanceof CommandHandler) {
            ((CommandHandler) handler).setBot(this.bot);
        }
        return this;
    }

    @Override
    public UpdateReceiver bot(TelegramBot bot) {
        this.bot = bot;

        TelegramOptions telegramOptions = bot.getOptions();

        HttpServerOptions options = new HttpServerOptions()
                .setKeyStoreOptions(new JksOptions()
                        .setPath(telegramOptions.getKeystorePath())
                        .setPassword(telegramOptions.getKeystorePassword()))
                .setSsl(true)
                .setCompressionSupported(true)
                .setUseAlpn(true);
        server = bot.getVertx().createHttpServer(options);
        return this;
    }

    @Override
    public UpdateReceiver start() {
        server.listen();
        server.requestHandler(request -> {
            if (request.path().startsWith(bot.getOptions().getBotToken())) {
                request
                        .bodyHandler(body -> {
                            JsonObject json = body.toJsonObject();
                            JsonArray updates = json.getJsonArray(Util.R_RESULT);
                            if (updates != null && updates.size() > 0) {
                                updates.stream().forEach(u -> {
                                    try {
                                        Update update = mapper.readValue(u.toString(), Update.class);
                                        if (update.getUpdateId() > lastReceivedUpdate) {
                                            lastReceivedUpdate = update.getUpdateId();
                                            if (handler != null) {
                                                try {
                                                    handler.handle(update);
                                                } catch (Exception e) {
                                                    log.error("### Exception in update handler: ", e);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        log.error("### Unable to parse received update: ", e);
                                    }
                                });
                            }
                        })
                        .exceptionHandler(exception -> {
                            log.warn("### Error on receiving update: ", exception);
                        });
            }
        });
        //todo self signed certificate, internal\external url`s
        bot.setWebhook(new SetWebhook().setUrl(bot.getOptions().getUrl()));
        return this;
    }

    @Override
    public UpdateReceiver stop() {
        bot.deleteWebhook();
        server.close();
        return this;
    }
}
