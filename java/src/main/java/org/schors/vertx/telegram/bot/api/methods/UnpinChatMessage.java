package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnpinChatMessage extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    public UnpinChatMessage() {
    }

    public UnpinChatMessage(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public UnpinChatMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public String getMethod() {
        return "unpinChatMessage";
    }
}
