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

public class Message {

    @JsonProperty("message_id")
    private Integer messageId;
    private User from;
    private Integer date;
    private Chat chat;
    @JsonProperty("forward_from")
    private User forwardFrom;
    @JsonProperty("forward_from_chat")
    private Chat forwardFromChat;
    @JsonProperty("forward_from_message_id")
    private Integer forwardFromMessageId;
    @JsonProperty("forward_date")
    private Integer forwardDate;
    @JsonProperty("reply_to_message")
    private Message replyToMessage;
    @JsonProperty("edit_date")
    private Integer editDate;
    private String text;
    private MessageEntity[] entities;
    private Audio audio;
    private Document document;
    private Game game;
    private PhotoSize[] photo;
    private Sticker sticker;
    private Video video;
    private Voice voice;
    private String caption;
    private Contact contact;
    private Location location;
    private Venue venue;
    @JsonProperty("new_chat_member")
    private User newChatMember;
    @JsonProperty("left_chat_member")
    private User leftChatMember;
    @JsonProperty("new_chat_title")
    private String newChatTitle;
    @JsonProperty("new_chat_photo")
    private PhotoSize[] newChatPhoto;
    @JsonProperty("delete_chat_photo")
    private Boolean deleteChatPhoto;
    @JsonProperty("group_chat_created")
    private Boolean groupChatCreated;
    @JsonProperty("supergroup_chat_created")
    private Boolean supergroupChatCreated;
    @JsonProperty("channel_chat_created")
    private Boolean channelChatCreated;
    @JsonProperty("migrate_to_chat_id")
    private Integer migrateToChatId;
    @JsonProperty("migrate_from_chat_id")
    private Integer migrateFromChatId;
    @JsonProperty("pinned_message")
    private Message pinnedMessage;

    public Message() {
    }

    public Message(Integer messageId, User from, Integer date, Chat chat, User forwardFrom, Chat forwardFromChat, Integer forwardFromMessageId, Integer forwardDate, Message replyToMessage, Integer editDate, String text, MessageEntity[] entities, Audio audio, Document document, Game game, PhotoSize[] photo, Sticker sticker, Video video, Voice voice, String caption, Contact contact, Location location, Venue venue, User newChatMember, User leftChatMember, String newChatTitle, PhotoSize[] newChatPhoto, Boolean deleteChatPhoto, Boolean groupChatCreated, Boolean supergroupChatCreated, Boolean channelChatCreated, Integer migrateToChatId, Integer migrateFromChatId, Message pinnedMessage) {
        this.messageId = messageId;
        this.from = from;
        this.date = date;
        this.chat = chat;
        this.forwardFrom = forwardFrom;
        this.forwardFromChat = forwardFromChat;
        this.forwardFromMessageId = forwardFromMessageId;
        this.forwardDate = forwardDate;
        this.replyToMessage = replyToMessage;
        this.editDate = editDate;
        this.text = text;
        this.entities = entities;
        this.audio = audio;
        this.document = document;
        this.game = game;
        this.photo = photo;
        this.sticker = sticker;
        this.video = video;
        this.voice = voice;
        this.caption = caption;
        this.contact = contact;
        this.location = location;
        this.venue = venue;
        this.newChatMember = newChatMember;
        this.leftChatMember = leftChatMember;
        this.newChatTitle = newChatTitle;
        this.newChatPhoto = newChatPhoto;
        this.deleteChatPhoto = deleteChatPhoto;
        this.groupChatCreated = groupChatCreated;
        this.supergroupChatCreated = supergroupChatCreated;
        this.channelChatCreated = channelChatCreated;
        this.migrateToChatId = migrateToChatId;
        this.migrateFromChatId = migrateFromChatId;
        this.pinnedMessage = pinnedMessage;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public Message setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public Message setFrom(User from) {
        this.from = from;
        return this;
    }

    public Integer getDate() {
        return date;
    }

    public Message setDate(Integer date) {
        this.date = date;
        return this;
    }

    public Chat getChat() {
        return chat;
    }

    public Message setChat(Chat chat) {
        this.chat = chat;
        return this;
    }

    public User getForwardFrom() {
        return forwardFrom;
    }

    public Message setForwardFrom(User forwardFrom) {
        this.forwardFrom = forwardFrom;
        return this;
    }

    public Chat getForwardFromChat() {
        return forwardFromChat;
    }

    public Message setForwardFromChat(Chat forwardFromChat) {
        this.forwardFromChat = forwardFromChat;
        return this;
    }

    public Integer getForwardFromMessageId() {
        return forwardFromMessageId;
    }

    public Message setForwardFromMessageId(Integer forwardFromMessageId) {
        this.forwardFromMessageId = forwardFromMessageId;
        return this;
    }

    public Integer getForwardDate() {
        return forwardDate;
    }

    public Message setForwardDate(Integer forwardDate) {
        this.forwardDate = forwardDate;
        return this;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public Message setReplyToMessage(Message replyToMessage) {
        this.replyToMessage = replyToMessage;
        return this;
    }

    public Integer getEditDate() {
        return editDate;
    }

    public Message setEditDate(Integer editDate) {
        this.editDate = editDate;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public MessageEntity[] getEntities() {
        return entities;
    }

    public Message setEntities(MessageEntity[] entities) {
        this.entities = entities;
        return this;
    }

    public Audio getAudio() {
        return audio;
    }

    public Message setAudio(Audio audio) {
        this.audio = audio;
        return this;
    }

    public Document getDocument() {
        return document;
    }

    public Message setDocument(Document document) {
        this.document = document;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public Message setGame(Game game) {
        this.game = game;
        return this;
    }

    public PhotoSize[] getPhoto() {
        return photo;
    }

    public Message setPhoto(PhotoSize[] photo) {
        this.photo = photo;
        return this;
    }

    public Sticker getSticker() {
        return sticker;
    }

    public Message setSticker(Sticker sticker) {
        this.sticker = sticker;
        return this;
    }

    public Video getVideo() {
        return video;
    }

    public Message setVideo(Video video) {
        this.video = video;
        return this;
    }

    public Voice getVoice() {
        return voice;
    }

    public Message setVoice(Voice voice) {
        this.voice = voice;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Message setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public Contact getContact() {
        return contact;
    }

    public Message setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Message setLocation(Location location) {
        this.location = location;
        return this;
    }

    public Venue getVenue() {
        return venue;
    }

    public Message setVenue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public User getNewChatMember() {
        return newChatMember;
    }

    public Message setNewChatMember(User newChatMember) {
        this.newChatMember = newChatMember;
        return this;
    }

    public User getLeftChatMember() {
        return leftChatMember;
    }

    public Message setLeftChatMember(User leftChatMember) {
        this.leftChatMember = leftChatMember;
        return this;
    }

    public String getNewChatTitle() {
        return newChatTitle;
    }

    public Message setNewChatTitle(String newChatTitle) {
        this.newChatTitle = newChatTitle;
        return this;
    }

    public PhotoSize[] getNewChatPhoto() {
        return newChatPhoto;
    }

    public Message setNewChatPhoto(PhotoSize[] newChatPhoto) {
        this.newChatPhoto = newChatPhoto;
        return this;
    }

    public Boolean isDeleteChatPhoto() {
        return deleteChatPhoto;
    }

    public Message setDeleteChatPhoto(Boolean deleteChatPhoto) {
        this.deleteChatPhoto = deleteChatPhoto;
        return this;
    }

    public Boolean isGroupChatCreated() {
        return groupChatCreated;
    }

    public Message setGroupChatCreated(Boolean groupChatCreated) {
        this.groupChatCreated = groupChatCreated;
        return this;
    }

    public Boolean isSupergroupChatCreated() {
        return supergroupChatCreated;
    }

    public Message setSupergroupChatCreated(Boolean supergroupChatCreated) {
        this.supergroupChatCreated = supergroupChatCreated;
        return this;
    }

    public Boolean isChannelChatCreated() {
        return channelChatCreated;
    }

    public Message setChannelChatCreated(Boolean channelChatCreated) {
        this.channelChatCreated = channelChatCreated;
        return this;
    }

    public Integer getMigrateToChatId() {
        return migrateToChatId;
    }

    public Message setMigrateToChatId(Integer migrateToChatId) {
        this.migrateToChatId = migrateToChatId;
        return this;
    }

    public Integer getMigrateFromChatId() {
        return migrateFromChatId;
    }

    public Message setMigrateFromChatId(Integer migrateFromChatId) {
        this.migrateFromChatId = migrateFromChatId;
        return this;
    }

    public Message getPinnedMessage() {
        return pinnedMessage;
    }

    public Message setPinnedMessage(Message pinnedMessage) {
        this.pinnedMessage = pinnedMessage;
        return this;
    }

    public String getChatId() {
        return chat.getId().toString();
    }

    public boolean hasText() {
        return text != null;
    }
}
