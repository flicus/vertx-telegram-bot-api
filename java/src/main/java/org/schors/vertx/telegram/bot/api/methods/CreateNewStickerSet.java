package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;
import org.schors.vertx.telegram.bot.api.types.MaskPosition;

import java.io.File;

public class CreateNewStickerSet extends TelegramMethod {

    @JsonProperty("user_id")
    private Integer userId;
    private String name;
    private String title;
    private String emojis;
    @JsonProperty("contains_masks")
    private Boolean containsMasks;
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

    public CreateNewStickerSet() {
    }

    public CreateNewStickerSet(Integer userId, String name, String title, String emojis, Boolean containsMasks, MaskPosition maskPosition) {
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.emojis = emojis;
        this.containsMasks = containsMasks;
        this.maskPosition = maskPosition;
    }

    public Integer getUserId() {
        return userId;
    }

    public CreateNewStickerSet setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateNewStickerSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CreateNewStickerSet setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getEmojis() {
        return emojis;
    }

    public CreateNewStickerSet setEmojis(String emojis) {
        this.emojis = emojis;
        return this;
    }

    public Boolean getContainsMasks() {
        return containsMasks;
    }

    public CreateNewStickerSet setContainsMasks(Boolean containsMasks) {
        this.containsMasks = containsMasks;
        return this;
    }

    public MaskPosition getMaskPosition() {
        return maskPosition;
    }

    public CreateNewStickerSet setMaskPosition(MaskPosition maskPosition) {
        this.maskPosition = maskPosition;
        return this;
    }

    public String getSticker() {
        return sticker;
    }

    public CreateNewStickerSet setSticker(String sticker) {
        this.sticker = sticker;
        return this;
    }

    public File getFile() {
        return file;
    }

    public CreateNewStickerSet setFile(File file) {
        this.file = file;
        return this;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public CreateNewStickerSet setStream(ReadStream<Buffer> stream) {
        this.stream = stream;
        return this;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public CreateNewStickerSet setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    @Override
    public String getMethod() {
        return "createNewStickerSet";
    }
}
