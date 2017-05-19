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
import org.schors.vertx.telegram.bot.api.types.Markup;

public class SendVideoNote extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("video_note")
    private String videoNote;
    private Integer duration;
    private Integer length;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private Markup replyMarkup;

    public SendVideoNote() {
    }

    public SendVideoNote(String chatId, String videoNote, Integer duration, Integer length, Boolean disableNotification, Integer replyToMessageId, Markup replyMarkup) {
        this.chatId = chatId;
        this.videoNote = videoNote;
        this.duration = duration;
        this.length = length;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    public String getChatId() {
        return chatId;
    }

    public SendVideoNote setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getVideoNote() {
        return videoNote;
    }

    public SendVideoNote setVideoNote(String videoNote) {
        this.videoNote = videoNote;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SendVideoNote setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public SendVideoNote setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Boolean isDisableNotification() {
        return disableNotification;
    }

    public SendVideoNote setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendVideoNote setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public Markup getReplyMarkup() {
        return replyMarkup;
    }

    public SendVideoNote setReplyMarkup(Markup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendVideoNote";
    }
}
