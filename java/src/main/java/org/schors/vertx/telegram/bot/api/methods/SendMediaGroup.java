package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.schors.vertx.telegram.bot.api.types.InputMedia;

public class SendMediaGroup extends TelegramMethod {
    @JsonProperty("chat_id")
    private String chatId;
    private InputMedia[] media;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;

    public SendMediaGroup() {
    }

    public SendMediaGroup(String chatId, InputMedia[] media, Boolean disableNotification, Integer replyToMessageId) {
        this.chatId = chatId;
        this.media = media;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
    }

    public String getChatId() {
        return chatId;
    }

    public SendMediaGroup setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public InputMedia[] getMedia() {
        return media;
    }

    public SendMediaGroup setMedia(InputMedia[] media) {
        this.media = media;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public SendMediaGroup setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendMediaGroup setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendMediaGroup";
    }
}
