package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PromoteChatMember extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("can_change_info")
    private Boolean canChangeInfo;
    @JsonProperty("can_post_messages")
    private Boolean canPostMessages;
    @JsonProperty("can_edit_messages")
    private Boolean canEditMessages;
    @JsonProperty("can_delete_messages")
    private Boolean canDeleteMessages;
    @JsonProperty("can_invite_users")
    private Boolean canInviteUsers;
    @JsonProperty("can_restrict_members")
    private Boolean canRestrictMembers;
    @JsonProperty("can_pin_messages")
    private Boolean canPinMessages;
    @JsonProperty("can_promote_members")
    private Boolean canPromoteMembers;

    public PromoteChatMember() {
    }

    public PromoteChatMember(String chatId, Integer userId) {
        this.chatId = chatId;
        this.userId = userId;
    }

    public PromoteChatMember(String chatId, Integer userId, Boolean canChangeInfo, Boolean canPostMessages, Boolean canEditMessages, Boolean canDeleteMessages, Boolean canInviteUsers, Boolean canRestrictMembers, Boolean canPinMessages, Boolean canPromoteMembers) {
        this.chatId = chatId;
        this.userId = userId;
        this.canChangeInfo = canChangeInfo;
        this.canPostMessages = canPostMessages;
        this.canEditMessages = canEditMessages;
        this.canDeleteMessages = canDeleteMessages;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessages = canPinMessages;
        this.canPromoteMembers = canPromoteMembers;
    }

    public String getChatId() {
        return chatId;
    }

    public PromoteChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public PromoteChatMember setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Boolean getCanChangeInfo() {
        return canChangeInfo;
    }

    public PromoteChatMember setCanChangeInfo(Boolean canChangeInfo) {
        this.canChangeInfo = canChangeInfo;
        return this;
    }

    public Boolean getCanPostMessages() {
        return canPostMessages;
    }

    public PromoteChatMember setCanPostMessages(Boolean canPostMessages) {
        this.canPostMessages = canPostMessages;
        return this;
    }

    public Boolean getCanEditMessages() {
        return canEditMessages;
    }

    public PromoteChatMember setCanEditMessages(Boolean canEditMessages) {
        this.canEditMessages = canEditMessages;
        return this;
    }

    public Boolean getCanDeleteMessages() {
        return canDeleteMessages;
    }

    public PromoteChatMember setCanDeleteMessages(Boolean canDeleteMessages) {
        this.canDeleteMessages = canDeleteMessages;
        return this;
    }

    public Boolean getCanInviteUsers() {
        return canInviteUsers;
    }

    public PromoteChatMember setCanInviteUsers(Boolean canInviteUsers) {
        this.canInviteUsers = canInviteUsers;
        return this;
    }

    public Boolean getCanRestrictMembers() {
        return canRestrictMembers;
    }

    public PromoteChatMember setCanRestrictMembers(Boolean canRestrictMembers) {
        this.canRestrictMembers = canRestrictMembers;
        return this;
    }

    public Boolean getCanPinMessages() {
        return canPinMessages;
    }

    public PromoteChatMember setCanPinMessages(Boolean canPinMessages) {
        this.canPinMessages = canPinMessages;
        return this;
    }

    public Boolean getCanPromoteMembers() {
        return canPromoteMembers;
    }

    public PromoteChatMember setCanPromoteMembers(Boolean canPromoteMembers) {
        this.canPromoteMembers = canPromoteMembers;
        return this;
    }

    @Override
    public String getMethod() {
        return "promoteChatMember";
    }
}
