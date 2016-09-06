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


import io.vertx.core.net.ProxyOptions;

public class TelegramOptions {

    private String botName;
    private String botToken;
    private int pollingTimeout = 70000;
    private int maxConnections = 200;
    private ProxyOptions proxyOptions;

    public String getBotName() {
        return botName;
    }

    public TelegramOptions setBotName(String botName) {
        this.botName = botName;
        return this;
    }

    public String getBotToken() {
        return botToken;
    }

    public TelegramOptions setBotToken(String botToken) {
        this.botToken = botToken;
        return this;
    }

    public int getPollingTimeout() {
        return pollingTimeout;
    }

    public TelegramOptions setPollingTimeout(int pollingTimeout) {
        this.pollingTimeout = pollingTimeout;
        return this;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public TelegramOptions setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public ProxyOptions getProxyOptions() {
        return proxyOptions;
    }

    public TelegramOptions setProxyOptions(ProxyOptions proxyOptions) {
        this.proxyOptions = proxyOptions;
        return this;
    }
}
