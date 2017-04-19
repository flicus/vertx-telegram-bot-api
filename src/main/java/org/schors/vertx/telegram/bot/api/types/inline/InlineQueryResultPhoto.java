package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultPhoto extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("photo_url")
    private String photoUrl;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("photo_width")
    private Integer photoWidth;
    @JsonProperty("photo_height")
    private Integer photoHeight;
    private String title;
    private String description;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultPhoto() {
        this.type = Type.photo;
    }

    public InlineQueryResultPhoto(Type type, String id, String photoUrl, String thumbUrl, Integer photoWidth, Integer photoHeight, String title, String description, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.photoUrl = photoUrl;
        this.thumbUrl = thumbUrl;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.title = title;
        this.description = description;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultPhoto setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultPhoto setId(String id) {
        this.id = id;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public InlineQueryResultPhoto setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public InlineQueryResultPhoto setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public Integer getPhotoWidth() {
        return photoWidth;
    }

    public InlineQueryResultPhoto setPhotoWidth(Integer photoWidth) {
        this.photoWidth = photoWidth;
        return this;
    }

    public Integer getPhotoHeight() {
        return photoHeight;
    }

    public InlineQueryResultPhoto setPhotoHeight(Integer photoHeight) {
        this.photoHeight = photoHeight;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultPhoto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InlineQueryResultPhoto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultPhoto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultPhoto setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultPhoto setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
