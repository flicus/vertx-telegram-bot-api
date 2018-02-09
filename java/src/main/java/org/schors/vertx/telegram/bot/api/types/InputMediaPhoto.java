package org.schors.vertx.telegram.bot.api.types;

public class InputMediaPhoto extends InputMedia {
    private String type;

    public InputMediaPhoto(String media, String caption) {
        super(media, caption);
        this.type = "photo";
    }

    public InputMediaPhoto() {
        this.type = "photo";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
