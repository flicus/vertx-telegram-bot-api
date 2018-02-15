package org.schors.vertx.telegram.bot.api.types.sticker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StickerSet {

    private String name;
    private String title;
    @JsonProperty("contains_masks")
    private Boolean containsMasks;
    private Sticker[] stickers;

    public StickerSet() {
    }

    public StickerSet(String name, String title, Boolean containsMasks, Sticker[] stickers) {
        this.name = name;
        this.title = title;
        this.containsMasks = containsMasks;
        this.stickers = stickers;
    }

    public String getName() {
        return name;
    }

    public StickerSet setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public StickerSet setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getContainsMasks() {
        return containsMasks;
    }

    public StickerSet setContainsMasks(Boolean containsMasks) {
        this.containsMasks = containsMasks;
        return this;
    }

    public Sticker[] getStickers() {
        return stickers;
    }

    public StickerSet setStickers(Sticker[] stickers) {
        this.stickers = stickers;
        return this;
    }
}
