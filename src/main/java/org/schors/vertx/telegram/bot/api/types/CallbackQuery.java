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

public class CallbackQuery {

    private String id;
    private User from;
    private Message message;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    @JsonProperty("chat_instance")
    private String chatInstance;
    private String data;
    @JsonProperty("game_short_name")
    private String gameShortName;

    public CallbackQuery() {
    }

    public CallbackQuery(String id, User from, Message message, String inlineMessageId, String chatInstance, String data, String gameShortName) {
        this.id = id;
        this.from = from;
        this.message = message;
        this.inlineMessageId = inlineMessageId;
        this.chatInstance = chatInstance;
        this.data = data;
        this.gameShortName = gameShortName;
    }

    public String getId() {
        return id;
    }

    public CallbackQuery setId(String id) {
        this.id = id;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public CallbackQuery setFrom(User from) {
        this.from = from;
        return this;
    }

    public Message getMessage() {
        return message;
    }

    public CallbackQuery setMessage(Message message) {
        this.message = message;
        return this;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public CallbackQuery setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public String getChatInstance() {
        return chatInstance;
    }

    public CallbackQuery setChatInstance(String chatInstance) {
        this.chatInstance = chatInstance;
        return this;
    }

    public String getData() {
        return data;
    }

    public CallbackQuery setData(String data) {
        this.data = data;
        return this;
    }

    public String getGameShortName() {
        return gameShortName;
    }

    public CallbackQuery setGameShortName(String gameShortName) {
        this.gameShortName = gameShortName;
        return this;
    }
}
