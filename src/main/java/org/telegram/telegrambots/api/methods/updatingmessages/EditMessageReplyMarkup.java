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

package org.telegram.telegrambots.api.methods.updatingmessages;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to edit only the reply markup of messages sent by the bot or via the bot
 * (for inline bots). On success, the edited Message is returned.
 * @date 10 of April of 2016
 */
public class EditMessageReplyMarkup extends BotApiMethod<Message> {
    public static final String PATH = "editmessagereplymarkup";

    private static final String CHATID_FIELD = "chat_id";
    private static final String MESSAGEID_FIELD = "message_id";
    private static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
    private static final String REPLYMARKUP_FIELD = "reply_markup";

    /**
     * Required if inline_message_id is not specified. Unique identifier for the chat to send the
     * message to (Or username for channels)
     */
    private String chatId;
    /**
     * Required if inline_message_id is not specified. Unique identifier of the sent message
     */
    private Integer messageId;
    /**
     * Required if chat_id and message_id are not specified. Identifier of the inline message
     */
    private String inlineMessageId;
    private InlineKeyboardMarkup replyMarkup; ///< Optional. A JSON-serialized object for an inline keyboard.

    public EditMessageReplyMarkup() {
        super();
    }

    public String getChatId() {
        return chatId;
    }

    public EditMessageReplyMarkup setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public EditMessageReplyMarkup setMessageId(Integer messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public EditMessageReplyMarkup setInlineMessageId(String inlineMessageId) {
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public EditMessageReplyMarkup setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        if (inlineMessageId == null) {
            jsonObject.put(CHATID_FIELD, chatId);
            jsonObject.put(MESSAGEID_FIELD, messageId);
        } else {
            jsonObject.put(INLINE_MESSAGE_ID_FIELD, inlineMessageId);
        }
        if (replyMarkup != null) {
            jsonObject.put(REPLYMARKUP_FIELD, replyMarkup.toJson());
        }
        return jsonObject;
    }

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public Message deserializeResponse(JsonObject answer) { //todo error
        if (answer.getBoolean(Constants.RESPONSEFIELDOK)) {
            return new Message(answer.getJsonObject(Constants.RESPONSEFIELDRESULT));
        }
        return null;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(METHOD_FIELD, PATH);
        if (inlineMessageId == null) {
            gen.writeStringField(CHATID_FIELD, chatId);
            gen.writeNumberField(MESSAGEID_FIELD, messageId);
        } else {
            gen.writeStringField(INLINE_MESSAGE_ID_FIELD, inlineMessageId);
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
        return "EditMessageReplyMarkup{" +
                "chatId=" + chatId +
                ", messageId=" + messageId +
                ", inlineMessageId=" + inlineMessageId +
                ", replyMarkup=" + replyMarkup +
                '}';
    }
}
