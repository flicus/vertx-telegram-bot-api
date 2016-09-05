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

import io.vertx.core.AbstractVerticle;
import org.schors.vertx.telegram.LongPollingReceiver;
import org.schors.vertx.telegram.TelegramBot;
import org.schors.vertx.telegram.TelegramOptions;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class TestVerticle extends AbstractVerticle {

    TelegramBot bot;

    @Override
    public void start() throws Exception {
        TelegramOptions telegramOptions = new TelegramOptions()
                .setBotName("evlampia_bot")
                .setBotToken("219739200:AAHXCuDWJPoRhUAjFBXFmljVJhR2uVXdmwc");

        bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(LongPollingReceiver.create().onUpdate(event -> {
                    System.out.println(event.getMessage().getText());
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(event.getMessage().getChatId());
                    sendMessage.setText("hi");
                    bot.sendMessage(sendMessage);
                }))
                .start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        bot.stop();
    }
}
