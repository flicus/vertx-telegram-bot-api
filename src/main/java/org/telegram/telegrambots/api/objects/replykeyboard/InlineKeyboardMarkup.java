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
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represents an inline keyboard that appears right next to the message it
 * belongs to
 * @note Inline keyboards are currently being tested and are only available in one-on-one chats
 * (i.e., user-bot or user-user in the case of inline bots).
 * @date 10 of April of 2016
 */
public class InlineKeyboardMarkup implements ReplyKeyboard {

    private static final String KEYBOARD_FIELD = "inline_keyboard";
    @JsonProperty(KEYBOARD_FIELD)
    private List<List<InlineKeyboardButton>> keyboard; ///< Array of button rows, each represented by an Array of Strings

    public InlineKeyboardMarkup() {
        super();
        keyboard = new ArrayList<>();
    }

    public List<List<InlineKeyboardButton>> getKeyboard() {
        return keyboard;
    }

    public InlineKeyboardMarkup setKeyboard(List<List<InlineKeyboardButton>> keyboard) {
        this.keyboard = keyboard;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonkeyboard = new JsonArray();

        for (List<InlineKeyboardButton> innerRow : this.keyboard) {
            JsonArray innerJSONKeyboard = new JsonArray();
            for (InlineKeyboardButton element : innerRow) {
                innerJSONKeyboard.add(element.toJson());
            }
            jsonkeyboard.add(innerJSONKeyboard);
        }
        jsonObject.put(InlineKeyboardMarkup.KEYBOARD_FIELD, jsonkeyboard);

        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeArrayFieldStart(KEYBOARD_FIELD);
        for (List<InlineKeyboardButton> innerRow : keyboard) {
            gen.writeStartArray();
            for (InlineKeyboardButton element : innerRow) {
                gen.writeObject(element);
            }
            gen.writeEndArray();
        }
        gen.writeEndArray();
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "InlineKeyboardMarkup{" +
                "inline_keyboard=" + keyboard +
                '}';
    }
}
