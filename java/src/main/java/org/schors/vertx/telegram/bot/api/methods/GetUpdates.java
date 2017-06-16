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

package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUpdates extends TelegramMethod {

    private Integer offset;
    private Integer limit;
    private Integer timeout;
    @JsonProperty("allowed_updates")
    private String[] allowedUpdates;

    public GetUpdates() {
    }

    public GetUpdates(Integer offset, Integer limit, Integer timeout, String[] allowedUpdates) {
        this.offset = offset;
        this.limit = limit;
        this.timeout = timeout;
        this.allowedUpdates = allowedUpdates;
    }

    public Integer getOffset() {
        return offset;
    }

    public GetUpdates setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public GetUpdates setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public GetUpdates setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    public String[] getAllowedUpdates() {
        return allowedUpdates;
    }

    public GetUpdates setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = allowedUpdates;
        return this;
    }

    @Override
    public String getMethod() {
        return "getUpdates";
    }
}
