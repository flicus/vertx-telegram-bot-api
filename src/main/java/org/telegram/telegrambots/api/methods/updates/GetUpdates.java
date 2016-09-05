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

package org.telegram.telegrambots.api.methods.updates;

import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.interfaces.IToJson;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to receive incoming updates using long polling (wiki). An Array of Update
 * objects is returned.
 * @date 20 of June of 2015
 */
public class GetUpdates implements IToJson {
    public static final String PATH = "getupdates";

    private static final String OFFSET_FIELD = "offset";
    private static final String LIMIT_FIELD = "limit";
    private static final String TIMEOUT_FIELD = "timeout";
    /**
     * Optional. Identifier of the first update to be returned. Must be greater by one than the
     * highest among the identifiers of previously received updates. By default, updates starting
     * with the earliest unconfirmed update are returned. An update is considered confirmed as soon
     * as getUpdates is called with an offset higher than its update_id. The negative offset can be
     * specified to retrieve updates starting from -offset update from the end of the updates queue.
     * All previous updates will forgotten.
     */
    private Integer offset;
    /**
     * Optional	Limits the number of updates to be retrieved. Values between 1—100 are accepted.
     * Defaults to 100
     */
    private Integer limit = 100;
    /**
     * Optional	Timeout in seconds for long polling. Defaults to 0, i.e. usual short polling
     */
    private Integer timeout = Constants.GETUPDATESTIMEOUT;

    public GetUpdates() {
        super();
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

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        if (offset != null) {
            jsonObject.put(OFFSET_FIELD, offset);
        }
        if (limit != null) {
            jsonObject.put(LIMIT_FIELD, limit);
        }
        if (timeout != null) {
            jsonObject.put(TIMEOUT_FIELD, timeout);
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return "GetUpdates{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", timeout=" + timeout +
                '}';
    }
}
