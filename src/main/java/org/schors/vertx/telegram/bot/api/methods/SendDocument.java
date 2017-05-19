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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import org.schors.vertx.telegram.bot.api.types.Markup;

import java.io.File;

public class SendDocument extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private String document;
    private String caption;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private Markup replyMarkup;

    @JsonIgnore
    private File file;
    @JsonIgnore
    private ReadStream<Buffer> stream;
    @JsonIgnore
    private String localFilePath;

    public SendDocument() {
    }

    public SendDocument(String chatId, String document, String caption, Boolean disableNotification, Integer replyToMessageId, Markup replyMarkup) {
        this.chatId = chatId;
        this.document = document;
        this.caption = caption;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    public String getChatId() {
        return chatId;
    }

    public SendDocument setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getDocument() {
        return document;
    }

    public SendDocument setDocument(String document) {
        this.document = document;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public SendDocument setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Boolean isDisableNotification() {
        return disableNotification;
    }

    public SendDocument setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendDocument setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public Markup getReplyMarkup() {
        return replyMarkup;
    }

    public SendDocument setReplyMarkup(Markup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public void setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public SendDocument setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendDocument";
    }
}
