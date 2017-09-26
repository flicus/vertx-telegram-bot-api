package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PinChatMessage extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;

    public PinChatMessage() {
    }

    public PinChatMessage(String chatId, Integer messageId, Boolean disableNotification) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.disableNotification = disableNotification;
    }

    public String getChatId() {
        return chatId;
    }

    public PinChatMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public PinChatMessage setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public PinChatMessage setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    @Override
    public String getMethod() {
        return "pinChatMessage";
    }
}
