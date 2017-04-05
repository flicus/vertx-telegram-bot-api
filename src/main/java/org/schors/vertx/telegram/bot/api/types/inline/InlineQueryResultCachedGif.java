package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultCachedGif {

    private String type;
    private String id;
    @JsonProperty("gif_file_id")
    private String gifFileId;
    private String title;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultCachedGif() {
    }

    public InlineQueryResultCachedGif(String type, String id, String gifFileId, String title, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.gifFileId = gifFileId;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public String getType() {
        return type;
    }

    public InlineQueryResultCachedGif setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultCachedGif setId(String id) {
        this.id = id;
        return this;
    }

    public String getGifFileId() {
        return gifFileId;
    }

    public InlineQueryResultCachedGif setGifFileId(String gifFileId) {
        this.gifFileId = gifFileId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultCachedGif setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultCachedGif setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultCachedGif setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultCachedGif setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
