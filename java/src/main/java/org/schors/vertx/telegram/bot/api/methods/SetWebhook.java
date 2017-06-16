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

package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetWebhook extends TelegramMethod {
    private String url;
    private String certificate;
    @JsonProperty("max_connections")
    private String maxConnections;
    @JsonProperty("allowed_updates")
    private String[] allowedUpdates;

    public SetWebhook() {
        maxConnections = "40";
    }

    public SetWebhook(String url, String certificate, String maxConnections, String[] allowedUpdates) {
        this.url = url;
        this.certificate = certificate;
        this.maxConnections = maxConnections;
        this.allowedUpdates = allowedUpdates;
    }

    public String getUrl() {
        return url;
    }

    public SetWebhook setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCertificate() {
        return certificate;
    }

    public SetWebhook setCertificate(String certificate) {
        this.certificate = certificate;
        return this;
    }

    public String getMaxConnections() {
        return maxConnections;
    }

    public SetWebhook setMaxConnections(String maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public String[] getAllowedUpdates() {
        return allowedUpdates;
    }

    public SetWebhook setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
        return this;
    }

    @Override
    public String getMethod() {
        return "setWebhook";
    }
}
