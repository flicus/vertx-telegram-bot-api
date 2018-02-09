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

public class Chat {
    private Integer id;
    private String type;
    private String title;
    private String username;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("all_members_are_administrators")
    private Boolean allAdmins;
    private ChatPhoto photo;
    private String description;
    @JsonProperty("invite_link")
    private String inviteLink;
    @JsonProperty("pinned_message")
    private Message pinnedMessage;
    @JsonProperty("sticker_set_name")
    private String stickerSetName;
    @JsonProperty("can_set_sticker_set")
    private Boolean canSetStickerSet;

    public Chat() {
    }

    public Chat(Integer id, String type, String title, String username, String firstName, String lastName, Boolean allAdmins, ChatPhoto photo, String description, String inviteLink, Message pinnedMessage, String stickerSetName, Boolean canSetStickerSet) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.allAdmins = allAdmins;
        this.photo = photo;
        this.description = description;
        this.inviteLink = inviteLink;
        this.pinnedMessage = pinnedMessage;
        this.stickerSetName = stickerSetName;
        this.canSetStickerSet = canSetStickerSet;
    }

    public Boolean getAllAdmins() {
        return allAdmins;
    }

    public ChatPhoto getPhoto() {
        return photo;
    }

    public Chat setPhoto(ChatPhoto photo) {
        this.photo = photo;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Chat setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getInviteLink() {
        return inviteLink;
    }

    public Chat setInviteLink(String inviteLink) {
        this.inviteLink = inviteLink;
        return this;
    }

    public Message getPinnedMessage() {
        return pinnedMessage;
    }

    public Chat setPinnedMessage(Message pinnedMessage) {
        this.pinnedMessage = pinnedMessage;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Chat setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public Chat setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Chat setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Chat setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Chat setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Chat setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Boolean isAllAdmins() {
        return allAdmins;
    }

    public Chat setAllAdmins(Boolean allAdmins) {
        this.allAdmins = allAdmins;
        return this;
    }

    public String getStickerSetName() {
        return stickerSetName;
    }

    public Chat setStickerSetName(String stickerSetName) {
        this.stickerSetName = stickerSetName;
        return this;
    }

    public Boolean getCanSetStickerSet() {
        return canSetStickerSet;
    }

    public Chat setCanSetStickerSet(Boolean canSetStickerSet) {
        this.canSetStickerSet = canSetStickerSet;
        return this;
    }
}
