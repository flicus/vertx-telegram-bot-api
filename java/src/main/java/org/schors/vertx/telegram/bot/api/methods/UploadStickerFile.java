package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

import java.io.File;

public class UploadStickerFile extends TelegramMethod {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonIgnore
    private File file;
    @JsonIgnore
    private ReadStream<Buffer> stream;
    @JsonIgnore
    private String localFilePath;

    public UploadStickerFile() {
    }

    public UploadStickerFile(Integer userId) {
        this.userId = userId;
    }

    public UploadStickerFile(Integer userId, File file) {
        this.userId = userId;
        this.file = file;
    }

    public UploadStickerFile(Integer userId, ReadStream<Buffer> stream) {
        this.userId = userId;
        this.stream = stream;
    }

    public UploadStickerFile(Integer userId, String localFilePath) {
        this.userId = userId;
        this.localFilePath = localFilePath;
    }

    public Integer getUserId() {
        return userId;
    }

    public UploadStickerFile setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public File getFile() {
        return file;
    }

    public UploadStickerFile setFile(File file) {
        this.file = file;
        return this;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public UploadStickerFile setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
        return this;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public UploadStickerFile setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    @Override
    public String getMethod() {
        return "uploadStickerFile";
    }
}
