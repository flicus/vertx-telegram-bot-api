package org.schors.vertx.telegram.bot.api.types;

public class InputMediaVideo extends InputMedia {
    private String type;
    private Integer width;
    private Integer height;
    private Integer duration;

    public InputMediaVideo() {
        super();
        this.type = "video";
    }

    public InputMediaVideo(String media, String caption, Integer width, Integer height, Integer duration) {
        super(media, caption);
        this.width = width;
        this.height = height;
        this.duration = duration;
        this.type = "video";
    }

    public String getType() {
        return type;
    }

    public InputMediaVideo setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public InputMediaVideo setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public InputMediaVideo setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public InputMediaVideo setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }
}
