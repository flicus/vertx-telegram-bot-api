package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultCachedVideo extends InlineQueryResult {

    private Type type;
    private String id;
    @JsonProperty("video_file_id")
    private String videoFileId;
    private String title;
    private String description;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultCachedVideo() {
        this.type = Type.video;
    }

    public InlineQueryResultCachedVideo(Type type, String id, String videoFileId, String title, String description, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.videoFileId = videoFileId;
        this.title = title;
        this.description = description;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultCachedVideo setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultCachedVideo setId(String id) {
        this.id = id;
        return this;
    }

    public String getVideoFileId() {
        return videoFileId;
    }

    public InlineQueryResultCachedVideo setVideoFileId(String videoFileId) {
        this.videoFileId = videoFileId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultCachedVideo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InlineQueryResultCachedVideo setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultCachedVideo setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultCachedVideo setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultCachedVideo setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
