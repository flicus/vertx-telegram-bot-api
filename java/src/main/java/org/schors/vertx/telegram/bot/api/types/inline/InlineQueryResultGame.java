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

package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineQueryResultGame extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("game_short_name")
    private String gameShortName;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    public InlineQueryResultGame() {
        this.type = Type.game;
    }

    public InlineQueryResultGame(Type type, String id, String gameShortName, InlineKeyboardMarkup replyMarkup) {
        this.type = type;
        this.id = id;
        this.gameShortName = gameShortName;
        this.replyMarkup = replyMarkup;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultGame setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultGame setId(String id) {
        this.id = id;
        return this;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public InlineQueryResultGame setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultGame setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }
}
