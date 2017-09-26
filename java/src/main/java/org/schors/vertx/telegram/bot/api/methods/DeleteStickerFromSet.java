package org.schors.vertx.telegram.bot.api.methods;

public class DeleteStickerFromSet extends TelegramMethod {

    private String sticker;

    public DeleteStickerFromSet() {
    }

    public DeleteStickerFromSet(String sticker) {
        this.sticker = sticker;
    }

    public String getSticker() {
        return sticker;
    }

    public DeleteStickerFromSet setSticker(String sticker) {
        this.sticker = sticker;
        return this;
    }

    @Override
    public String getMethod() {
        return "deleteStickerFromSet";
    }
}
