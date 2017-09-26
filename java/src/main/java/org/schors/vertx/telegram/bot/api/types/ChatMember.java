/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016 schors
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMember {

    private User user;
    private String status;
    @JsonProperty("until_date")
    private Integer untilDate;
    @JsonProperty("can_be_edited")
    private Boolean canBeEdited;
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
    @JsonProperty("can_send_messages")
    private Boolean canSendMessages;
    @JsonProperty("can_send_media_messages")
    private Boolean canSendMediaMessages;
    @JsonProperty("can_send_other_messages")
    private Boolean canSendOtherMessages;
    @JsonProperty("can_add_web_page_previews")
    private Boolean canAddWebPagePreviews;

    public ChatMember() {
    }

    public ChatMember(User user, String status) {
        this.user = user;
        this.status = status;
    }

    public Integer getUntilDate() {
        return untilDate;
    }

    public ChatMember setUntilDate(Integer untilDate) {
        this.untilDate = untilDate;
        return this;
    }

    public Boolean getCanBeEdited() {
        return canBeEdited;
    }

    public ChatMember setCanBeEdited(Boolean canBeEdited) {
        this.canBeEdited = canBeEdited;
        return this;
    }

    public Boolean getCanChangeInfo() {
        return canChangeInfo;
    }

    public ChatMember setCanChangeInfo(Boolean canChangeInfo) {
        this.canChangeInfo = canChangeInfo;
        return this;
    }

    public Boolean getCanPostMessages() {
        return canPostMessages;
    }

    public ChatMember setCanPostMessages(Boolean canPostMessages) {
        this.canPostMessages = canPostMessages;
        return this;
    }

    public Boolean getCanEditMessages() {
        return canEditMessages;
    }

    public ChatMember setCanEditMessages(Boolean canEditMessages) {
        this.canEditMessages = canEditMessages;
        return this;
    }

    public Boolean getCanDeleteMessages() {
        return canDeleteMessages;
    }

    public ChatMember setCanDeleteMessages(Boolean canDeleteMessages) {
        this.canDeleteMessages = canDeleteMessages;
        return this;
    }

    public Boolean getCanInviteUsers() {
        return canInviteUsers;
    }

    public ChatMember setCanInviteUsers(Boolean canInviteUsers) {
        this.canInviteUsers = canInviteUsers;
        return this;
    }

    public Boolean getCanRestrictMembers() {
        return canRestrictMembers;
    }

    public ChatMember setCanRestrictMembers(Boolean canRestrictMembers) {
        this.canRestrictMembers = canRestrictMembers;
        return this;
    }

    public Boolean getCanPinMessages() {
        return canPinMessages;
    }

    public ChatMember setCanPinMessages(Boolean canPinMessages) {
        this.canPinMessages = canPinMessages;
        return this;
    }

    public Boolean getCanPromoteMembers() {
        return canPromoteMembers;
    }

    public ChatMember setCanPromoteMembers(Boolean canPromoteMembers) {
        this.canPromoteMembers = canPromoteMembers;
        return this;
    }

    public Boolean getCanSendMessages() {
        return canSendMessages;
    }

    public ChatMember setCanSendMessages(Boolean canSendMessages) {
        this.canSendMessages = canSendMessages;
        return this;
    }

    public Boolean getCanSendMediaMessages() {
        return canSendMediaMessages;
    }

    public ChatMember setCanSendMediaMessages(Boolean canSendMediaMessages) {
        this.canSendMediaMessages = canSendMediaMessages;
        return this;
    }

    public Boolean getCanSendOtherMessages() {
        return canSendOtherMessages;
    }

    public ChatMember setCanSendOtherMessages(Boolean canSendOtherMessages) {
        this.canSendOtherMessages = canSendOtherMessages;
        return this;
    }

    public Boolean getCanAddWebPagePreviews() {
        return canAddWebPagePreviews;
    }

    public ChatMember setCanAddWebPagePreviews(Boolean canAddWebPagePreviews) {
        this.canAddWebPagePreviews = canAddWebPagePreviews;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ChatMember setUser(User user) {
        this.user = user;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ChatMember setStatus(String status) {
        this.status = status;
        return this;
    }
}
