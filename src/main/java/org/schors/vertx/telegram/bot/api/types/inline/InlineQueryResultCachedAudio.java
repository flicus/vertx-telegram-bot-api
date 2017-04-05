package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultCachedAudio {

    private String type;
    private String id;
    @JsonProperty("audio_file_id")
    private String audioFileId;
    private String caption;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultCachedAudio() {
    }

    public InlineQueryResultCachedAudio(String type, String id, String audioFileId, String caption, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.audioFileId = audioFileId;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public String getType() {
        return type;
    }

    public InlineQueryResultCachedAudio setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultCachedAudio setId(String id) {
        this.id = id;
        return this;
    }

    public String getAudioFileId() {
        return audioFileId;
    }

    public InlineQueryResultCachedAudio setAudioFileId(String audioFileId) {
        this.audioFileId = audioFileId;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultCachedAudio setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultCachedAudio setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultCachedAudio setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
