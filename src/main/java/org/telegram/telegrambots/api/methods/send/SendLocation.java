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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to send point on the map. On success, the sent Message is returned.
 * @date 20 of June of 2015
 */
public class SendLocation extends BotApiMethod<Message> {
    public static final String PATH = "sendlocation";

    private static final String CHATID_FIELD = "chat_id";
    private static final String LATITUDE_FIELD = "latitude";
    private static final String LONGITUDE_FIELD = "longitude";
    private static final String DISABLENOTIFICATION_FIELD = "disable_notification";
    private static final String REPLYTOMESSAGEID_FIELD = "reply_to_message_id";
    private static final String REPLYMARKUP_FIELD = "reply_markup";
    private String chatId; ///< Unique identifier for the chat to send the message to (Or username for channels)
    private Float latitude; ///< Latitude of location
    private Float longitude; ///< Longitude of location
    /**
     * Optional. Sends the message silently. iOS users will not receive a notification, Android
     * users will receive a notification with no sound. Other apps coming soon
     */
    private Boolean disableNotification;
    private Integer replyToMessageId; ///< Optional. If the message is a reply, ID of the original message
    private ReplyKeyboard replyMarkup; ///< Optional. JSON-serialized object for a custom reply keyboard

    public String getChatId() {
        return chatId;
    }

    public SendLocation setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public SendLocation setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public SendLocation setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendLocation setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public ReplyKeyboard getReplyMarkup() {
        return replyMarkup;
    }

    public SendLocation setReplyMarkup(ReplyKeyboard replyMarkup) {
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
    public SendLocation setReplayToMessageId(Integer replyToMessageId) {
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
    public SendLocation setReplayMarkup(ReplyKeyboard replyMarkup) {
        return setReplyMarkup(replyMarkup);
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public SendLocation enableNotification() {
        this.disableNotification = false;
        return this;
    }

    public SendLocation disableNotification() {
        this.disableNotification = true;
        return this;
    }

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public Message deserializeResponse(JsonObject answer) {
        if (answer.getBoolean(Constants.RESPONSEFIELDOK)) {
            return new Message(answer.getJsonObject(Constants.RESPONSEFIELDRESULT));
        }
        return null;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(CHATID_FIELD, chatId);
        jsonObject.put(LATITUDE_FIELD, latitude);
        jsonObject.put(LONGITUDE_FIELD, longitude);
        if (disableNotification != null) {
            jsonObject.put(DISABLENOTIFICATION_FIELD, disableNotification);
        }
        if (replyToMessageId != null) {
            jsonObject.put(REPLYTOMESSAGEID_FIELD, replyToMessageId);
        }
        if (replyMarkup != null) {
            jsonObject.put(REPLYMARKUP_FIELD, replyMarkup.toJson());
        }

        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(METHOD_FIELD, PATH);
        gen.writeStringField(CHATID_FIELD, chatId);
        gen.writeNumberField(LATITUDE_FIELD, latitude);
        gen.writeNumberField(LONGITUDE_FIELD, longitude);
        if (disableNotification != null) {
            gen.writeBooleanField(DISABLENOTIFICATION_FIELD, disableNotification);
        }
        if (replyToMessageId != null) {
            gen.writeNumberField(REPLYTOMESSAGEID_FIELD, replyToMessageId);
        }
        if (replyMarkup != null) {
            gen.writeObjectField(REPLYMARKUP_FIELD, replyMarkup);
        }

        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "SendLocation{" +
                "chatId='" + chatId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", replyToMessageId=" + replyToMessageId +
                ", replyMarkup=" + replyMarkup +
                '}';
    }
}
