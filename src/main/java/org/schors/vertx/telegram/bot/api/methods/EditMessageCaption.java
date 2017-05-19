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
import org.schors.vertx.telegram.bot.api.types.inline.InlineKeyboardMarkup;

public class EditMessageCaption extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;

    public EditMessageCaption() {
    }

    public String getChatId() {
        return chatId;
    }

    public EditMessageCaption setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public EditMessageCaption setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public EditMessageCaption setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public EditMessageCaption setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public EditMessageCaption setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public String getMethod() {
        return "editMessageCaption";
    }
}
