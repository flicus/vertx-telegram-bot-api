/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2016  schors
 *
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

package org.telegram.telegrambots.api.methods.send;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to send .webp stickers. On success, the sent Message is returned.
 * @date 20 of June of 2015
 */
public class SendSticker {
    public static final String PATH = "sendsticker";

    public static final String CHATID_FIELD = "chat_id";
    public static final String STICKER_FIELD = "sticker";
    public static final String DISABLENOTIFICATION_FIELD = "disable_notification";
    public static final String REPLYTOMESSAGEID_FIELD = "reply_to_message_id";
    public static final String REPLYMARKUP_FIELD = "reply_markup";
    private String chatId; ///< Unique identifier for the chat to send the message to (Or username for channels)
    private String sticker; ///< Sticker file to send. file_id as String to resend a sticker that is already on the Telegram servers
    /**
     * Optional. Sends the message silently. iOS users will not receive a notification, Android
     * users will receive a notification with no sound. Other apps coming soon
     */
    private Boolean disableNotification;
    private Integer replyToMessageId; ///< Optional. If the message is a reply, ID of the original message
    private ReplyKeyboard replyMarkup; ///< Optional. JSON-serialized object for a custom reply keyboard

    private boolean isNewSticker; ///< True to upload a new sticker, false to use a fileId
    private String stickerName;
    private File newStickerFile; ///< New sticker file
    private InputStream newStickerStream; ///< New sticker stream

    public SendSticker() {
        super();
    }

    public String getChatId() {
        return chatId;
    }

    public SendSticker setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getSticker() {
        return sticker;
    }

    public SendSticker setSticker(String sticker) {
        this.sticker = sticker;
        this.isNewSticker = false;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendSticker setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public ReplyKeyboard getReplyMarkup() {
        return replyMarkup;
    }

    public SendSticker setReplyMarkup(ReplyKeyboard replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    /**
     * @deprecated Use {@link #getReplyToMessageId()} instead.
     */
    @Deprecated
    public Integer getReplayToMessageId() {
        return getReplyToMessageId();
    }

    /**
     * @deprecated Use {@link #setReplyToMessageId(Integer)} instead.
     */
    @Deprecated
    public SendSticker setReplayToMessageId(Integer replyToMessageId) {
        return setReplyToMessageId(replyToMessageId);
    }

    /**
     * @deprecated Use {@link #getReplyMarkup()} instead.
     */
    @Deprecated
    public ReplyKeyboard getReplayMarkup() {
        return getReplyMarkup();
    }

    /**
     * @deprecated Use {@link #setReplyMarkup(ReplyKeyboard)} instead.
     */
    @Deprecated
    public SendSticker setReplayMarkup(ReplyKeyboard replyMarkup) {
        return setReplyMarkup(replyMarkup);
    }

    /**
     * Use this method to set the sticker to a new file
     *
     * @param sticker     Path to the new file in your server
     * @param stickerName Name of the file itself
     * @deprecated use {@link #setNewSticker(File)} or {@link #setNewSticker(InputStream)} instead.
     */
    @Deprecated
    public SendSticker setSticker(String sticker, String stickerName) {
        this.sticker = sticker;
        this.isNewSticker = true;
        this.stickerName = stickerName;
        return this;
    }

    public SendSticker setNewSticker(String stickerName, InputStream inputStream) {
        Objects.requireNonNull(stickerName, "stickerName cannot be null!");
        Objects.requireNonNull(inputStream, "inputStream cannot be null!");
        this.stickerName = stickerName;
        this.isNewSticker = true;
        this.newStickerStream = inputStream;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public SendSticker enableNotification() {
        this.disableNotification = false;
        return this;
    }

    public SendSticker disableNotification() {
        this.disableNotification = true;
        return this;
    }

    public boolean isNewSticker() {
        return isNewSticker;
    }

    public SendSticker setNewSticker(File file) {
        this.sticker = file.getName();
        this.isNewSticker = true;
        this.newStickerFile = file;
        return this;
    }

    public String getStickerName() {
        return stickerName;
    }

    public File getNewStickerFile() {
        return newStickerFile;
    }

    public InputStream getNewStickerStream() {
        return newStickerStream;
    }

    @Override
    public String toString() {
        return "SendSticker{" +
                "chatId='" + chatId + '\'' +
                ", sticker='" + sticker + '\'' +
                ", replyToMessageId=" + replyToMessageId +
                ", replyMarkup=" + replyMarkup +
                ", isNewSticker=" + isNewSticker +
                '}';
    }
}
