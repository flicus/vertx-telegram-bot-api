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

public class ForwardMessage {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("from_chat_id")
    private String fromChatId;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("message_id")
    private Integer messageId;

    public ForwardMessage() {
    }

    public ForwardMessage(String chatId, String fromChatId, Boolean disableNotification, Integer messageId) {
        this.chatId = chatId;
        this.fromChatId = fromChatId;
        this.disableNotification = disableNotification;
        this.messageId = messageId;
    }

    public String getChatId() {
        return chatId;
    }

    public ForwardMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getFromChatId() {
        return fromChatId;
    }

    public ForwardMessage setFromChatId(String fromChatId) {
        this.fromChatId = fromChatId;
        return this;
    }

    public Boolean isDisableNotification() {
        return disableNotification;
    }

    public ForwardMessage setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public ForwardMessage setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }
}
