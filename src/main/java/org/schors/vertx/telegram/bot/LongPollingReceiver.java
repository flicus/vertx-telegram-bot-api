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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.log4j.Logger;
import org.schors.vertx.telegram.bot.api.methods.GetUpdates;
import org.schors.vertx.telegram.bot.api.types.Update;
import org.schors.vertx.telegram.bot.util.Util;

public class LongPollingReceiver implements UpdateReceiver {

    private static final Logger log = Logger.getLogger(LongPollingReceiver.class);

    private TelegramBot bot;
    private HttpClient client;

    private long taskId;
    private Handler<Update> handler;
    private PollHandler pollHandler = new PollHandler();
    private int lastReceivedUpdate = 0;
    private ObjectMapper mapper = new ObjectMapper();

    public LongPollingReceiver() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public static LongPollingReceiver create() {
        return new LongPollingReceiver();
    }

    @Override
    public UpdateReceiver bot(TelegramBot bot) {
        this.bot = bot;

        HttpClientOptions httpOptions = new HttpClientOptions()
                .setSsl(true)
                .setTrustAll(true)
                .setIdleTimeout(bot.getOptions().getPollingTimeout())
                .setMaxPoolSize(bot.getOptions().getMaxConnections())
                .setDefaultHost(Util.BASEHOST)
                .setDefaultPort(443)
                .setLogActivity(true);

        if (bot.getOptions().getProxyOptions() != null)
            httpOptions.setProxyOptions(bot.getOptions().getProxyOptions());

        client = bot.getVertx().createHttpClient(httpOptions);

        return this;
    }

    @Override
    public UpdateReceiver start() {
        runTimer(500);
        return this;
    }

    @Override
    public UpdateReceiver stop() {
        bot.getVertx().cancelTimer(taskId);
        return this;
    }

    @Override
    public UpdateReceiver onUpdate(Handler<Update> handler) {
        this.handler = handler;
        return this;
    }

    private void runTimer(int timeout) {
        taskId = bot.getVertx().setTimer(timeout, id -> pollHandler.handle(id));
    }

    public class PollHandler implements Handler {

        @Override
        public void handle(Object event) {
            GetUpdates getUpdates = new GetUpdates().setOffset(lastReceivedUpdate + 1).setTimeout(50);
            String toSend = null;
            try {
                toSend = mapper.writeValueAsString(getUpdates);
            } catch (JsonProcessingException e) {
                log.error("### Unable to serialize to JSON", e);
            }
            if (toSend != null)
                client
                        .post(Util.BASEURL + bot.getOptions().getBotToken() + "/" + getUpdates.getMethod())
                        .handler(response -> {
                            response.bodyHandler(body -> {
                                JsonObject json = body.toJsonObject();
                                if (!json.getBoolean(Util.R_OK)) {
                                    log.warn("### Unsuccessful response: " + json.toString());
                                } else {
                                    JsonArray updates = json.getJsonArray(Util.R_RESULT);
                                    if (updates != null && updates.size() > 0) {
                                        updates.stream().forEach(u -> {
                                            try {
                                                Update update = mapper.readValue(u.toString(), Update.class);
                                                if (update.getUpdateId() > lastReceivedUpdate) {
                                                    lastReceivedUpdate = update.getUpdateId();
                                                    if (bot.getCommandManager() != null) {
                                                        bot.getCommandManager().handle(update);
                                                    } else if (handler != null) {
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
                                }
                                runTimer(500);
                            });
                        })
                        .exceptionHandler(e -> {
                            log.error(e, e);
                            runTimer(500);
                        })
                        .setTimeout(75000)
                        .putHeader("Content-Type", "application/json")
                        .end(toSend, "UTF-8");
        }
    }
}
