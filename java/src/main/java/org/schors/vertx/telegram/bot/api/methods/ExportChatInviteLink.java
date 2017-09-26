package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExportChatInviteLink extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    public ExportChatInviteLink() {
    }

    public ExportChatInviteLink(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public ExportChatInviteLink setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public String getMethod() {
        return "exportChatInviteLink";
    }
}
