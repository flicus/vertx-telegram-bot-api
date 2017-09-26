package org.schors.vertx.telegram.bot.api.methods;

public class GetStickerSet extends TelegramMethod {

    private String name;

    public GetStickerSet() {
    }

    public GetStickerSet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GetStickerSet setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getMethod() {
        return "getStickerSet";
    }
}
