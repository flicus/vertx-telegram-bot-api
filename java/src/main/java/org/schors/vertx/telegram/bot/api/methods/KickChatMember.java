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

public class KickChatMember extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("until_date")
    private Integer untilDate;

    public KickChatMember() {
    }

    public KickChatMember(String chatId, Integer userId) {
        this.chatId = chatId;
        this.userId = userId;
    }

    public KickChatMember(String chatId, Integer userId, Integer untilDate) {
        this.chatId = chatId;
        this.userId = userId;
        this.untilDate = untilDate;
    }

    public String getChatId() {
        return chatId;
    }

    public KickChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public KickChatMember setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getUntilDate() {
        return untilDate;
    }

    public KickChatMember setUntilDate(Integer untilDate) {
        this.untilDate = untilDate;
        return this;
    }

    @Override
    public String getMethod() {
        return "kickChatMember";
    }
}
