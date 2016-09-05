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

package org.telegram.telegrambots.api.objects.replykeyboard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Upon receiving a message with this object, Telegram clients will hide the current custom
 * keyboard and display the default letter-keyboard. By default, custom keyboards are displayed
 * until a new keyboard is sent by a bot. An exception is made for one-time keyboards that are
 * hidden immediately after the user presses a button (@see ReplyKeyboardMarkup).
 * @date 20 of June of 2015
 */
public class ReplyKeyboardHide implements ReplyKeyboard {
    private static final String HIDEKEYBOARD_FIELD = "hide_keyboard";
    private static final String SELECTIVE_FIELD = "selective";
    @JsonProperty(HIDEKEYBOARD_FIELD)
    private Boolean hideKeyboard; ///< Requests clients to hide the custom keyboard
    /**
     * Optional. Use this parameter if you want to show the keyboard to specific users only.
     * Targets: 1) users that are @mentioned in the text of the Message object; 2) if the bot's
     * message is a reply (has reply_to_message_id), sender of the original message.
     */
    @JsonProperty(SELECTIVE_FIELD)
    private Boolean selective;

    public ReplyKeyboardHide() {
        super();
        this.hideKeyboard = true;
    }

    public ReplyKeyboardHide(JsonObject jsonObject) {
        super();
        if (jsonObject.containsKey(HIDEKEYBOARD_FIELD)) {
            this.hideKeyboard = jsonObject.getBoolean(HIDEKEYBOARD_FIELD);
        }
        if (jsonObject.containsKey(SELECTIVE_FIELD)) {
            this.selective = jsonObject.getBoolean(SELECTIVE_FIELD);
        }
    }

    public Boolean getHideKeyboard() {
        return hideKeyboard;
    }

    public Boolean getSelective() {
        return selective;
    }

    public ReplyKeyboardHide setSelective(Boolean selective) {
        this.selective = selective;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(HIDEKEYBOARD_FIELD, this.hideKeyboard);
        jsonObject.put(SELECTIVE_FIELD, this.selective);
        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField(HIDEKEYBOARD_FIELD, hideKeyboard);
        gen.writeBooleanField(SELECTIVE_FIELD, selective);
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "ReplyKeyboardHide{" +
                "hideKeyboard=" + hideKeyboard +
                ", selective=" + selective +
                '}';
    }
}
