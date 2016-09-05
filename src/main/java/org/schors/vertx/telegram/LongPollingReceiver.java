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

import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.updates.GetUpdates;
import org.telegram.telegrambots.api.objects.Update;

public class LongPollingReceiver implements UpdateReceiver {

    private TelegramBot bot;

    private long taskId;
    private Handler<Update> handler;
    private PollHandler pollHandler = new PollHandler();
    private int lastReceivedUpdate = 0;

    private LongPollingReceiver() {

    }

    public static LongPollingReceiver create() {
        return new LongPollingReceiver();
    }

    @Override
    public UpdateReceiver bot(TelegramBot bot) {
        this.bot = bot;
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
        System.out.println("Runtimer");
        taskId = bot.getVertx().setTimer(timeout, id -> pollHandler.handle(id));
    }

    public class PollHandler implements Handler {

        @Override
        public void handle(Object event) {
            String toSend = new GetUpdates().setOffset(lastReceivedUpdate + 1).toJson().encode();
            System.out.println(toSend);
            bot.getClient()
                    .post(Constants.BASEURL + bot.getOptions().getBotToken() + "/" + GetUpdates.PATH)
                    .handler(response -> {
                        System.out.println("new response: " + response.statusCode());
                        response.bodyHandler(body -> {
                            System.out.println(body.toString());
                            JsonObject json = body.toJsonObject();
                            if (!json.getBoolean(Constants.RESPONSEFIELDOK)) {
                                //todo some server error
                            }
                            JsonArray updates = json.getJsonArray(Constants.RESPONSEFIELDRESULT);
                            if (updates != null && updates.size() != 0) {
                                updates.stream().forEach(u -> {
                                    Update update = new Update((JsonObject) u);
                                    if (update.getUpdateId() > lastReceivedUpdate) {
                                        lastReceivedUpdate = update.getUpdateId();
                                        handler.handle(update);
                                    }
                                });
                            }
                            System.out.println("after response");
                            runTimer(500);
                        });
                    }).exceptionHandler(e -> {
                System.out.println(e.getMessage());
                runTimer(500);
            })
                    .setTimeout(75 * 1000)
                    .putHeader("Content-Type", "application/json")
                    .end(toSend, "UTF-8");
        }
    }
}
