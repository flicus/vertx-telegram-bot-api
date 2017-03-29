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
import io.vertx.core.net.ProxyOptions;
import io.vertx.core.net.ProxyType;
import org.schors.vertx.telegram.bot.LongPollingReceiver;
import org.schors.vertx.telegram.bot.TelegramBot;
import org.schors.vertx.telegram.bot.TelegramOptions;
import org.schors.vertx.telegram.bot.api.methods.SendMessage;

import java.io.FileInputStream;
import java.util.Properties;

public class TestVerticle extends AbstractVerticle {

    private TelegramBot bot;

    @Override
    public void start() throws Exception {
        Properties p = new Properties();
        p.load(new FileInputStream("bot.ini"));

        TelegramOptions telegramOptions = new TelegramOptions()
                .setBotName(p.getProperty("name"))
                .setBotToken(p.getProperty("token"))
                .setProxyOptions(new ProxyOptions()
                        .setType(ProxyType.HTTP)
                        .setHost("genproxy")
                        .setPort(8080));

        bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(new LongPollingReceiver().onUpdate(event -> {
                    bot.sendMessage(new SendMessage()
                            .setChatId(event.getMessage().getChatId())
                            .setText("hi"));
                }))
                .start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        bot.stop();
    }
}
