package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultVideo extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("mime_type")
    private String mimeType;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    private String title;
    private String caption;
    @JsonProperty("video_width")
    private Integer videoWidth;
    @JsonProperty("video_height")
    private Integer videoHeight;
    @JsonProperty("video_duration")
    private Integer videoDuration;
    private String description;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultVideo() {
        this.type = Type.video;
    }

    public InlineQueryResultVideo(Type type, String id, String videoUrl, String mimeType, String thumbUrl, String title, String caption, Integer videoWidth, Integer videoHeight, Integer videoDuration, String description, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.videoUrl = videoUrl;
        this.mimeType = mimeType;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.videoWidth = videoWidth;
        this.videoHeight = videoHeight;
        this.videoDuration = videoDuration;
        this.description = description;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultVideo setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultVideo setId(String id) {
        this.id = id;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public InlineQueryResultVideo setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getMimeType() {
        return mimeType;
    }

    public InlineQueryResultVideo setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public InlineQueryResultVideo setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultVideo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultVideo setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Integer getVideoWidth() {
        return videoWidth;
    }

    public InlineQueryResultVideo setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
        return this;
    }

    public Integer getVideoHeight() {
        return videoHeight;
    }

    public InlineQueryResultVideo setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
        return this;
    }

    public Integer getVideoDuration() {
        return videoDuration;
    }

    public InlineQueryResultVideo setVideoDuration(Integer videoDuration) {
        this.videoDuration = videoDuration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public InlineQueryResultVideo setDescription(String description) {
        this.description = description;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultVideo setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultVideo setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
