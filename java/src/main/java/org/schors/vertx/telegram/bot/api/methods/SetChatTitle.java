package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetChatTitle extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private String title;

    public SetChatTitle() {
    }

    public SetChatTitle(String chatId, String title) {
        this.chatId = chatId;
        this.title = title;
    }

    public String getChatId() {
        return chatId;
    }

    public SetChatTitle setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SetChatTitle setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public String getMethod() {
        return "setChatTitle";
    }
}
