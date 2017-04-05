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

package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.Location;
import org.schors.vertx.telegram.bot.api.types.User;

public class ChosenInlineResult {

    @JsonProperty("result_id")
    private String resultId;
    private User from;
    private Location location;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    private String query;

    public ChosenInlineResult() {
    }

    public ChosenInlineResult(String resultId, User from, Location location, String inlineMessageId, String query) {
        this.resultId = resultId;
        this.from = from;
        this.location = location;
        this.inlineMessageId = inlineMessageId;
        this.query = query;
    }

    public String getResultId() {
        return resultId;
    }

    public ChosenInlineResult setResultId(String resultId) {
        this.resultId = resultId;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public ChosenInlineResult setFrom(User from) {
        this.from = from;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public ChosenInlineResult setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public ChosenInlineResult setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public ChosenInlineResult setQuery(String query) {
        this.query = query;
        return this;
    }
}
