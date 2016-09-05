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

package org.telegram.telegrambots.api.methods;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.objects.Message;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to send text messages. On success, the sent Message is returned.
 * @date 20 of June of 2015
 */
public class ForwardMessage extends BotApiMethod<Message> {
    public static final String PATH = "forwardmessage";

    private static final String CHATID_FIELD = "chat_id";
    private static final String FROMCHATID_FIELD = "from_chat_id";
    private static final String MESSAGEID_FIELD = "message_id";
    private static final String DISABLENOTIFICATION_FIELD = "disable_notification";
    private String chatId; ///< Unique identifier for the chat to send the message to (or username for channels)
    private String fromChatId; ///< Unique identifier for the chat where the original message was sent — User or GroupChat id
    private Integer messageId; ///< Unique message identifier
    /**
     * Optional. Sends the message silently.
     * iOS users will not receive a notification,
     * Android users will receive a notification with no sound.
     * Other apps coming soon
     */
    private Boolean disableNotification;

    public ForwardMessage() {
        super();
    }

    public String getChatId() {
        return chatId;
    }

    public ForwardMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Deprecated
    public ForwardMessage setFromChatId(Integer fromChatId) {
        this.fromChatId = fromChatId.toString();
        return this;
    }

    public String getFromChatId() {
        return fromChatId;
    }

    public ForwardMessage setFromChatId(String fromChatId) {
        this.fromChatId = fromChatId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public ForwardMessage setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public void enableNotification() {
        this.disableNotification = false;
    }

    public void disableNotification() {
        this.disableNotification = true;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(METHOD_FIELD, PATH);
        gen.writeStringField(CHATID_FIELD, chatId);
        gen.writeStringField(FROMCHATID_FIELD, fromChatId);
        gen.writeNumberField(MESSAGEID_FIELD, messageId);
        if (disableNotification != null) {
            gen.writeBooleanField(DISABLENOTIFICATION_FIELD, disableNotification);
        }
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(CHATID_FIELD, chatId);
        jsonObject.put(FROMCHATID_FIELD, fromChatId);
        jsonObject.put(MESSAGEID_FIELD, messageId);
        if (disableNotification != null) {
            jsonObject.put(DISABLENOTIFICATION_FIELD, disableNotification);
        }
        return jsonObject;
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
    public String toString() {
        return "ForwardMessage{" +
                "chatId='" + chatId + '\'' +
                ", fromChatId=" + fromChatId +
                ", messageId=" + messageId +
                '}';
    }
}
