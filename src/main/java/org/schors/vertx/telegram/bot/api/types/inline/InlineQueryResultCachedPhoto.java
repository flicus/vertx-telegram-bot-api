package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultCachedPhoto extends InlineQueryResult {

    private Type type;
    private String id;
    private String title;
    @JsonProperty("photo_file_id")
    private String photoFileId;
    private String description;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultCachedPhoto() {
        this.type = Type.photo;
    }

    public InlineQueryResultCachedPhoto(Type type, String id, String title, String photoFileId, String description, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.photoFileId = photoFileId;
        this.description = description;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultCachedPhoto setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultCachedPhoto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultCachedPhoto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPhotoFileId() {
        return photoFileId;
    }

    public InlineQueryResultCachedPhoto setPhotoFileId(String photoFileId) {
        this.photoFileId = photoFileId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InlineQueryResultCachedPhoto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultCachedPhoto setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultCachedPhoto setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultCachedPhoto setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
