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

import org.schors.vertx.telegram.bot.api.types.Location;
import org.schors.vertx.telegram.bot.api.types.User;

public class InlineQuery {

    private String id;
    private User from;
    private Location location;
    private String query;
    private String offset;

    public InlineQuery() {
    }

    public InlineQuery(String id, User from, Location location, String query, String offset) {
        this.id = id;
        this.from = from;
        this.location = location;
        this.query = query;
        this.offset = offset;
    }

    public String getId() {
        return id;
    }

    public InlineQuery setId(String id) {
        this.id = id;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public InlineQuery setFrom(User from) {
        this.from = from;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public InlineQuery setLocation(Location location) {
        this.location = location;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public InlineQuery setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getOffset() {
        return offset;
    }

    public InlineQuery setOffset(String offset) {
        this.offset = offset;
        return this;
    }
}
