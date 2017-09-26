package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteChatPhoto extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    public DeleteChatPhoto() {
    }

    public DeleteChatPhoto(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public DeleteChatPhoto setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public String getMethod() {
        return "deleteChatPhoto";
    }
}
