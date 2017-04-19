package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultGif extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("gif_url")
    private String gifUrl;
    @JsonProperty("gif_width")
    private Integer gifWidth;
    @JsonProperty("gif_height")
    private Integer gifHeight;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    private String title;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultGif() {
        this.type = Type.gif;
    }

    public InlineQueryResultGif(Type type, String id, String gifUrl, Integer gifWidth, Integer gifHeight, String thumbUrl, String title, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.gifUrl = gifUrl;
        this.gifWidth = gifWidth;
        this.gifHeight = gifHeight;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultGif setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultGif setId(String id) {
        this.id = id;
        return this;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public InlineQueryResultGif setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public Integer getGifWidth() {
        return gifWidth;
    }

    public InlineQueryResultGif setGifWidth(Integer gifWidth) {
        this.gifWidth = gifWidth;
        return this;
    }

    public Integer getGifHeight() {
        return gifHeight;
    }

    public InlineQueryResultGif setGifHeight(Integer gifHeight) {
        this.gifHeight = gifHeight;
        return this;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public InlineQueryResultGif setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultGif setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultGif setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultGif setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultGif setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
