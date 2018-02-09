package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteChatStickerSet extends TelegramMethod {
    @JsonProperty("chat_id")
    private String chatId;

    public DeleteChatStickerSet() {
    }

    public DeleteChatStickerSet(String chatId) {
        this.chatId = chatId;
    }

    public String getChatId() {
        return chatId;
    }

    public DeleteChatStickerSet setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public String getMethod() {
        return "deleteChatStickerSet";
    }
}
