package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

import java.io.File;

public class SetChatPhoto extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonIgnore
    private File file;
    @JsonIgnore
    private ReadStream<Buffer> stream;
    @JsonIgnore
    private String localFilePath;

    public SetChatPhoto() {
    }

    public SetChatPhoto(String chatId, File file) {
        this.chatId = chatId;
        this.file = file;
    }

    public SetChatPhoto(String chatId, ReadStream<Buffer> stream) {
        this.chatId = chatId;
        this.stream = stream;
    }

    public SetChatPhoto(String chatId, String localFilePath) {
        this.chatId = chatId;
        this.localFilePath = localFilePath;
    }

    public String getChatId() {
        return chatId;
    }

    public SetChatPhoto setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public File getFile() {
        return file;
    }

    public SetChatPhoto setFile(File file) {
        this.file = file;
        return this;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public SetChatPhoto setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
        return this;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public SetChatPhoto setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    @Override
    public String getMethod() {
        return "setChatPhoto";
    }
}
