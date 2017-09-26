package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestrictChatMember extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("until_date")
    private Integer untilDate;
    @JsonProperty("can_send_messages")
    private Boolean canSendMessages;
    @JsonProperty("can_send_media_messages")
    private Boolean canSendMediaMessages;
    @JsonProperty("can_send_other_messages")
    private Boolean canSendOtherMessages;
    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

    public RestrictChatMember() {
    }

    public RestrictChatMember(String chatId, Integer userId, Integer untilDate, Boolean canSendMessages, Boolean canSendMediaMessages, Boolean canSendOtherMessages, Boolean canAddWebPagePreviews) {
        this.chatId = chatId;
        this.userId = userId;
        this.untilDate = untilDate;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
    }

    public RestrictChatMember(String chatId, Integer userId) {
        this.chatId = chatId;
        this.userId = userId;
    }

    public String getChatId() {
        return chatId;
    }

    public RestrictChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public RestrictChatMember setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getUntilDate() {
        return untilDate;
    }

    public RestrictChatMember setUntilDate(Integer untilDate) {
        this.untilDate = untilDate;
        return this;
    }

    public Boolean getCanSendMessages() {
        return canSendMessages;
    }

    public RestrictChatMember setCanSendMessages(Boolean canSendMessages) {
        this.canSendMessages = canSendMessages;
        return this;
    }

    public Boolean getCanSendMediaMessages() {
        return canSendMediaMessages;
    }

    public RestrictChatMember setCanSendMediaMessages(Boolean canSendMediaMessages) {
        this.canSendMediaMessages = canSendMediaMessages;
        return this;
    }

    public Boolean getCanSendOtherMessages() {
        return canSendOtherMessages;
    }

    public RestrictChatMember setCanSendOtherMessages(Boolean canSendOtherMessages) {
        this.canSendOtherMessages = canSendOtherMessages;
        return this;
    }

    public Boolean getCanAddWebPagePreviews() {
        return canAddWebPagePreviews;
    }

    public RestrictChatMember setCanAddWebPagePreviews(Boolean canAddWebPagePreviews) {
        this.canAddWebPagePreviews = canAddWebPagePreviews;
        return this;
    }

    @Override
    public String getMethod() {
        return "restrictChatMember";
    }
}
