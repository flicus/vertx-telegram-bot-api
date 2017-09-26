package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetChatDescription extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private String description;

    public SetChatDescription() {
    }

    public SetChatDescription(String chatId, String description) {
        this.chatId = chatId;
        this.description = description;
    }

    public String getChatId() {
        return chatId;
    }

    public SetChatDescription setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SetChatDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String getMethod() {
        return "setChatDescription";
    }
}
