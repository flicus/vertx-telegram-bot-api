package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import org.schors.vertx.telegram.bot.api.types.MaskPosition;

import java.io.File;

public class AddStickerToSet extends TelegramMethod<Boolean> {

    @JsonProperty("user_id")
    private Integer userId;
    private String name;
    private String emojis;
    @JsonProperty("mask_position")
    private MaskPosition maskPosition;

    @JsonIgnore
    private String sticker; //can be file_id or url

    //otherwise some of these
    @JsonIgnore
    private File file;
    @JsonIgnore
    private ReadStream<Buffer> stream;
    @JsonIgnore
    private String localFilePath;

    public AddStickerToSet() {
    }

    public AddStickerToSet(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public AddStickerToSet(Integer userId, String name, String emojis) {
        this.userId = userId;
        this.name = name;
        this.emojis = emojis;
    }

    public Integer getUserId() {
        return userId;
    }

    public AddStickerToSet setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public AddStickerToSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmojis() {
        return emojis;
    }

    public AddStickerToSet setEmojis(String emojis) {
        this.emojis = emojis;
        return this;
    }

    public MaskPosition getMaskPosition() {
        return maskPosition;
    }

    public AddStickerToSet setMaskPosition(MaskPosition maskPosition) {
        this.maskPosition = maskPosition;
        return this;
    }

    public String getSticker() {
        return sticker;
    }

    public AddStickerToSet setSticker(String sticker) {
        this.sticker = sticker;
        return this;
    }

    public File getFile() {
        return file;
    }

    public AddStickerToSet setFile(File file) {
        this.file = file;
        return this;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public AddStickerToSet setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
        return this;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public AddStickerToSet setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    @Override
    public String getMethod() {
        return "addStickerToSet";
    }
}
