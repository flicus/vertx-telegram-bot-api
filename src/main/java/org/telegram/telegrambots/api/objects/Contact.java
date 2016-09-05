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

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief This object represents a phone contact.
 * @date 20 of June of 2015
 */
public class Contact implements IBotApiObject {

    private static final String PHONENUMBER_FIELD = "phone_number";
    private static final String FIRSTNAME_FIELD = "first_name";
    private static final String LASTNAME_FIELD = "last_name";
    private static final String USERID_FIELD = "user_id";
    @JsonProperty(PHONENUMBER_FIELD)
    private String phoneNumber; ///< Contact's phone number
    @JsonProperty(FIRSTNAME_FIELD)
    private String firstName; ///< Contact's first name
    @JsonProperty(LASTNAME_FIELD)
    private String lastName; ///< Optional. Contact's last name
    @JsonProperty(USERID_FIELD)
    private Integer userID; ///< Optional. Contact's user identifier in Telegram

    public Contact() {
        super();
    }

    public Contact(JsonObject jsonObject) {
        super();
        this.phoneNumber = jsonObject.getString(PHONENUMBER_FIELD);
        this.firstName = jsonObject.getString(FIRSTNAME_FIELD);
        if (jsonObject.containsKey(LASTNAME_FIELD)) {
            this.lastName = jsonObject.getString(LASTNAME_FIELD);
        }
        if (jsonObject.containsKey(USERID_FIELD)) {
            this.userID = jsonObject.getInteger(USERID_FIELD);
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getUserID() {
        return userID;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(PHONENUMBER_FIELD, phoneNumber);
        gen.writeStringField(FIRSTNAME_FIELD, firstName);
        if (lastName != null) {
            gen.writeStringField(LASTNAME_FIELD, lastName);
        }
        if (userID != null) {
            gen.writeNumberField(USERID_FIELD, userID);
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
        return "Contact{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userID=" + userID +
                '}';
    }
}
