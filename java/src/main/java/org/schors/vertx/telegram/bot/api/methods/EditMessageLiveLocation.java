package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.Markup;

public class EditMessageLiveLocation extends TelegramMethod {
    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("inline_message_id")
    private String inlineMessageId;
    private Double latitude;
    private Double longitude;
    @JsonProperty("reply_markup")
    private Markup replyMarkup;

    public EditMessageLiveLocation() {
    }

    public EditMessageLiveLocation(String chatId, Integer messageId, String inlineMessageId, Double latitude, Double longitude, Markup replyMarkup) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = inlineMessageId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.replyMarkup = replyMarkup;
    }

    public String getChatId() {
        return chatId;
    }

    public EditMessageLiveLocation setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public EditMessageLiveLocation setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public EditMessageLiveLocation setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public EditMessageLiveLocation setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public EditMessageLiveLocation setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Markup getReplyMarkup() {
        return replyMarkup;
    }

    public EditMessageLiveLocation setReplyMarkup(Markup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public String getMethod() {
        return "editMessageLiveLocation";
    }
}
