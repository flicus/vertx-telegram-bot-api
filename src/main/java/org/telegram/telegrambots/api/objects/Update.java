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

package org.telegram.telegrambots.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;
import org.telegram.telegrambots.api.objects.inlinequery.ChosenInlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represents an incoming update.
 * Only one of the optional parameters can be present in any given update.
 * @date 20 of June of 2015
 */
public class Update implements IBotApiObject {
    private static final String UPDATEID_FIELD = "update_id";
    private static final String MESSAGE_FIELD = "message";
    private static final String INLINEQUERY_FIELD = "inline_query";
    private static final String CHOSENINLINEQUERY_FIELD = "chosen_inline_result";
    private static final String CALLBACKQUERY_FIELD = "callback_query";
    private static final String EDITEDMESSAGE_FIELD = "edited_message";
    @JsonProperty(UPDATEID_FIELD)
    private Integer updateId;
    @JsonProperty(MESSAGE_FIELD)
    private Message message; ///< Optional. New incoming message of any kind — text, photo, sticker, etc.
    @JsonProperty(INLINEQUERY_FIELD)
    private InlineQuery inlineQuery; ///< Optional. New incoming inline query
    @JsonProperty(CHOSENINLINEQUERY_FIELD)
    private ChosenInlineQuery chosenInlineQuery; ///< Optional. The result of a inline query that was chosen by a user and sent to their chat partner
    @JsonProperty(CALLBACKQUERY_FIELD)
    private CallbackQuery callbackQuery; ///< Optional. New incoming callback query
    @JsonProperty(EDITEDMESSAGE_FIELD)
    private Message editedMessage; ///< Optional. New version of a message that is known to the bot and was edited

    public Update() {
        super();
    }

    public Update(JsonObject jsonObject) {
        super();
        this.updateId = jsonObject.getInteger(UPDATEID_FIELD);
        if (jsonObject.containsKey(MESSAGE_FIELD)) {
            this.message = new Message(jsonObject.getJsonObject(MESSAGE_FIELD));
        }
        if (jsonObject.containsKey(INLINEQUERY_FIELD)) {
            this.inlineQuery = new InlineQuery(jsonObject.getJsonObject(INLINEQUERY_FIELD));
        }
        if (jsonObject.containsKey(CHOSENINLINEQUERY_FIELD)) {
            this.chosenInlineQuery = new ChosenInlineQuery(jsonObject.getJsonObject(CHOSENINLINEQUERY_FIELD));
        }
        if (jsonObject.containsKey(CALLBACKQUERY_FIELD)) {
            callbackQuery = new CallbackQuery(jsonObject.getJsonObject(CALLBACKQUERY_FIELD));
        }
        if (jsonObject.containsKey(EDITEDMESSAGE_FIELD)) {
            editedMessage = new Message(jsonObject.getJsonObject(EDITEDMESSAGE_FIELD));
        }
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public Message getMessage() {
        return message;
    }

    public InlineQuery getInlineQuery() {
        return inlineQuery;
    }

    public ChosenInlineQuery getChosenInlineQuery() {
        return chosenInlineQuery;
    }

    public CallbackQuery getCallbackQuery() {
        return callbackQuery;
    }

    public Message getEditedMessage() {
        return editedMessage;
    }

    public boolean hasMessage() {
        return message != null;
    }

    public boolean hasInlineQuery() {
        return inlineQuery != null;
    }

    public boolean hasChosenInlineQuery() {
        return chosenInlineQuery != null;
    }

    public boolean hasCallbackQuery() {
        return callbackQuery != null;
    }

    public boolean hasEditedMessage() {
        return editedMessage != null;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(UPDATEID_FIELD, updateId);
        if (message != null) {
            gen.writeObjectField(MESSAGE_FIELD, message);
        }
        if (inlineQuery != null) {
            gen.writeObjectField(INLINEQUERY_FIELD, inlineQuery);
        }
        if (chosenInlineQuery != null) {
            gen.writeObjectField(CHOSENINLINEQUERY_FIELD, chosenInlineQuery);
        }
        if (callbackQuery != null) {
            gen.writeObjectField(CALLBACKQUERY_FIELD, callbackQuery);
        }
        if (editedMessage != null) {
            gen.writeObjectField(EDITEDMESSAGE_FIELD, editedMessage);
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
        return "Update{" +
                "updateId=" + updateId +
                ", message=" + message +
                ", inlineQuery=" + inlineQuery +
                ", chosenInlineQuery=" + chosenInlineQuery +
                ", callbackQuery=" + callbackQuery +
                ", editedMessage=" + editedMessage +
                '}';
    }
}
