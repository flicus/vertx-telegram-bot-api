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

package org.telegram.telegrambots.api.objects.replykeyboard.buttons;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;
import org.telegram.telegrambots.api.interfaces.IToJson;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represents one button of the reply keyboard. For simple text buttons String
 * can be used instead of this object to specify text of the button.
 * @note Optional fields are mutually exclusive.
 * @note request_contact and request_location options will only work in Telegram versions released
 * after 9 April, 2016. Older clients will ignore them.
 * @date 10 of April of 2016
 */
public class KeyboardButton implements IBotApiObject, IToJson {

    private static final String TEXT_FIELD = "text";
    private static final String REQUEST_CONTACT_FIELD = "request_contact";
    private static final String REQUEST_LOCATION_FIELD = "request_location";
    @JsonProperty(TEXT_FIELD)
    /**
     * Text of the button.
     * If none of the optional fields are used, it will be sent to the bot as a message when the button is pressed
     */
    private String text;
    @JsonProperty(REQUEST_CONTACT_FIELD)
    /**
     * Optional.
     * If True, the user's phone number will be sent as a contact when the button is pressed.
     * Available in private chats only
     */
    private Boolean requestContact;
    @JsonProperty(REQUEST_LOCATION_FIELD)
    /**
     * Optional.
     * If True, the user's current location will be sent when the button is pressed.
     * Available in private chats only
     */
    private Boolean requestLocation;

    public KeyboardButton() {
        super();
    }

    public KeyboardButton(String text) {
        super();
        this.text = text;
    }

    public KeyboardButton(JsonObject jsonObject) {
        super();
        text = jsonObject.getString(TEXT_FIELD);
        if (jsonObject.containsKey(REQUEST_CONTACT_FIELD)) {
            requestContact = jsonObject.getBoolean(REQUEST_CONTACT_FIELD);
        }
        if (jsonObject.containsKey(REQUEST_LOCATION_FIELD)) {
            requestLocation = jsonObject.getBoolean(REQUEST_LOCATION_FIELD);
        }
    }

    public String getText() {
        return text;
    }

    public KeyboardButton setText(String text) {
        this.text = text;
        return this;
    }

    public Boolean getRequestContact() {
        return requestContact;
    }

    public KeyboardButton setRequestContact(Boolean requestContact) {
        this.requestContact = requestContact;
        return this;
    }

    public Boolean getRequestLocation() {
        return requestLocation;
    }

    public KeyboardButton setRequestLocation(Boolean requestLocation) {
        this.requestLocation = requestLocation;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(TEXT_FIELD, text);
        if (requestContact != null) {
            jsonObject.put(REQUEST_CONTACT_FIELD, requestContact);
        }
        if (requestLocation != null) {
            jsonObject.put(REQUEST_LOCATION_FIELD, requestLocation);
        }

        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(TEXT_FIELD, text);
        if (requestContact != null) {
            gen.writeBooleanField(REQUEST_CONTACT_FIELD, requestContact);
        }
        if (requestLocation != null) {
            gen.writeBooleanField(REQUEST_LOCATION_FIELD, requestLocation);
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
        return "KeyboardButton{" +
                "text=" + text +
                ", requestContact=" + requestContact +
                ", requestLocation=" + requestLocation +
                '}';
    }
}
