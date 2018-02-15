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
import org.schors.vertx.telegram.bot.api.types.keyboard.Markup;

import java.io.File;

public class SendVideo extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private String video;
    private String caption;
    private Integer duration;
    private Integer width;
    private Integer height;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private Markup replyMarkup;
    @JsonProperty("supports_streaming ")
    private Boolean supportsStreaming;

    @JsonIgnore
    private File file;
    @JsonIgnore
    private ReadStream<Buffer> stream;
    @JsonIgnore
    private String localFilePath;

    public SendVideo() {
    }

    public SendVideo(String chatId, String video, String caption, Integer duration, Integer width, Integer height, Boolean disableNotification, Integer replyToMessageId, Markup replyMarkup, Boolean supportsStreaming, File file, ReadStream<Buffer> stream, String localFilePath) {
        this.chatId = chatId;
        this.video = video;
        this.caption = caption;
        this.duration = duration;
        this.width = width;
        this.height = height;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
        this.supportsStreaming = supportsStreaming;
        this.file = file;
        this.stream = stream;
        this.localFilePath = localFilePath;
    }

    public String getChatId() {
        return chatId;
    }

    public SendVideo setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getVideo() {
        return video;
    }

    public SendVideo setVideo(String video) {
        this.video = video;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public SendVideo setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SendVideo setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public SendVideo setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public SendVideo setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Boolean isDisableNotification() {
        return disableNotification;
    }

    public SendVideo setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendVideo setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public Markup getReplyMarkup() {
        return replyMarkup;
    }

    public SendVideo setReplyMarkup(Markup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public File getFile() {
        return file;
    }

    public SendVideo setFile(File file) {
        this.file = file;
        return this;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public SendVideo setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
        return this;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public SendVideo setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public Boolean getSupportsStreaming() {
        return supportsStreaming;
    }

    public SendVideo setSupportsStreaming(Boolean supportsStreaming) {
        this.supportsStreaming = supportsStreaming;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendVideo";
    }


}
