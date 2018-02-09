package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetChatStickerSet extends TelegramMethod {
    @JsonProperty("sticker_set_name")
    protected String stickerSetName;
    @JsonProperty("chat_id")
    private String chatId;

    public SetChatStickerSet() {
    }

    public SetChatStickerSet(String chatId, String stickerSetName) {
        this.chatId = chatId;
        this.stickerSetName = stickerSetName;
    }

    public String getChatId() {
        return chatId;
    }

    public SetChatStickerSet setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getStickerSetName() {
        return stickerSetName;
    }

    public SetChatStickerSet setStickerSetName(String stickerSetName) {
        this.stickerSetName = stickerSetName;
        return this;
    }

    @Override
    public String getMethod() {
        return "setChatStickerSet";
    }
}
