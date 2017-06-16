/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016 schors
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

package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookInfo {

    private String url;
    @JsonProperty("has_custom_certificate")
    private Boolean hasCustomCertificate;
    @JsonProperty("pending_update_count")
    private Integer pendingUpdateCount;
    @JsonProperty("last_error_date")
    private Integer lastErrorDate;
    @JsonProperty("last_error_message")
    private String lastErrorMessage;
    @JsonProperty("max_connections")
    private Integer maxConnections;
    @JsonProperty("allowed_updates")
    private String[] allowedUpdates;

    public WebhookInfo() {
    }

    public WebhookInfo(String url, Boolean hasCustomCertificate, Integer pendingUpdateCount, Integer lastErrorDate, String lastErrorMessage, Integer maxConnections, String[] allowedUpdates) {
        this.url = url;
        this.hasCustomCertificate = hasCustomCertificate;
        this.pendingUpdateCount = pendingUpdateCount;
        this.lastErrorDate = lastErrorDate;
        this.lastErrorMessage = lastErrorMessage;
        this.maxConnections = maxConnections;
        this.allowedUpdates = allowedUpdates;
    }

    public String getUrl() {
        return url;
    }

    public WebhookInfo setUrl(String url) {
        this.url = url;
        return this;
    }

    public Boolean isHasCustomCertificate() {
        return hasCustomCertificate;
    }

    public WebhookInfo setHasCustomCertificate(Boolean hasCustomCertificate) {
        this.hasCustomCertificate = hasCustomCertificate;
        return this;
    }

    public Integer getPendingUpdateCount() {
        return pendingUpdateCount;
    }

    public WebhookInfo setPendingUpdateCount(Integer pendingUpdateCount) {
        this.pendingUpdateCount = pendingUpdateCount;
        return this;
    }

    public Integer getLastErrorDate() {
        return lastErrorDate;
    }

    public WebhookInfo setLastErrorDate(Integer lastErrorDate) {
        this.lastErrorDate = lastErrorDate;
        return this;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public WebhookInfo setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
        return this;
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public WebhookInfo setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
        return this;
    }

    public String[] getAllowedUpdates() {
        return allowedUpdates;
    }

    public WebhookInfo setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
        return this;
    }
}
