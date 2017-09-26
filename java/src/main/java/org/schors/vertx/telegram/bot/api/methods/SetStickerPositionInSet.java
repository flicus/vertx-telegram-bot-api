package org.schors.vertx.telegram.bot.api.methods;

public class SetStickerPositionInSet extends TelegramMethod {

    private String sticker;
    private Integer position;

    public SetStickerPositionInSet() {
    }

    public SetStickerPositionInSet(String sticker, Integer position) {
        this.sticker = sticker;
        this.position = position;
    }

    public String getSticker() {
        return sticker;
    }

    public SetStickerPositionInSet setSticker(String sticker) {
        this.sticker = sticker;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public SetStickerPositionInSet setPosition(Integer position) {
        this.position = position;
        return this;
    }

    @Override
    public String getMethod() {
        return "setStickerPositionInSet";
    }
}
