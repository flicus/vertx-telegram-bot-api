package org.schors.vertx.telegram.bot.api.types.inline;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMessageContent;

public class InlineQueryResultVoice extends InlineQueryResult {
    private Type type;
    private String id;
    @JsonProperty("voice_url")
    private String voiceUrl;
    private String title;
    private String caption;
    @JsonProperty("voice_duration")
    private Integer voiceDuration;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("input_message_content")
    private InputMessageContent inputMessageContent;

    public InlineQueryResultVoice() {
        this.type = Type.voice;
    }

    public InlineQueryResultVoice(Type type, String id, String voiceUrl, String title, String caption, Integer voiceDuration, InlineKeyboardMarkup replyMarkup, InputMessageContent inputMessageContent) {
        this.type = type;
        this.id = id;
        this.voiceUrl = voiceUrl;
        this.title = title;
        this.caption = caption;
        this.voiceDuration = voiceDuration;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;
    }

    public Type getType() {
        return type;
    }

    public InlineQueryResultVoice setType(Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultVoice setId(String id) {
        this.id = id;
        return this;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public InlineQueryResultVoice setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultVoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public InlineQueryResultVoice setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public InlineQueryResultVoice setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultVoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultVoice setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }
}
