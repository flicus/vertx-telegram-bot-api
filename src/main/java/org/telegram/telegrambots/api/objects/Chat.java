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
 * @brief This object represents a Telegram chat with an user or a group
 * @date 24 of June of 2015
 */
public class Chat implements IBotApiObject {
    private static final String ID_FIELD = "id";
    private static final String TYPE_FIELD = "type";
    private static final String TITLE_FIELD = "title";
    private static final String FIRSTNAME_FIELD = "first_name";
    private static final String LASTNAME_FIELD = "last_name";
    private static final String USERNAME_FIELD = "username";
    private static final String USERCHATTYPE = "private";
    private static final String GROUPCHATTYPE = "group";
    private static final String CHANNELCHATTYPE = "channel";
    private static final String SUPERGROUPCHATTYPE = "supergroup";
    @JsonProperty(ID_FIELD)
    /**
     * Unique identifier for this chat.
     * This number may be greater than 32 bits and some programming languages may
     * have difficulty/silent defects in interpreting it. But it smaller than 52 bits,
     * so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
     */
    private Long id; ///< Unique identifier for this chat, not exciding 1e13 by absolute value
    @JsonProperty(TYPE_FIELD)
    private String type; ///< Type of the chat, one of “private”, “group” or “channel”
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Optional. Title of the chat, only for channels and group chat
    @JsonProperty(FIRSTNAME_FIELD)
    private String firstName; ///< Optional. Username of the chat, only for private chats and channels if available
    @JsonProperty(LASTNAME_FIELD)
    private String lastName; ///< Optional. Interlocutor's first name for private chats
    @JsonProperty(USERNAME_FIELD)
    private String userName; ///< Optional. Interlocutor's last name for private chats

    public Chat() {
        super();
    }

    public Chat(JsonObject jsonObject) {
        super();
        this.id = jsonObject.getLong(ID_FIELD);
        this.type = jsonObject.getString(TYPE_FIELD);
        if (jsonObject.containsKey(TITLE_FIELD)) {
            this.title = jsonObject.getString(TITLE_FIELD);
        }
        if (jsonObject.containsKey(FIRSTNAME_FIELD)) {
            this.firstName = jsonObject.getString(FIRSTNAME_FIELD);
        }
        if (jsonObject.containsKey(LASTNAME_FIELD)) {
            this.lastName = jsonObject.getString(LASTNAME_FIELD);
        }
        if (jsonObject.containsKey(USERNAME_FIELD)) {
            this.userName = jsonObject.getString(USERNAME_FIELD);
        }
    }

    public Long getId() {
        return id;
    }

    public Boolean isGroupChat() {
        return GROUPCHATTYPE.equals(type);
    }

    public Boolean isChannelChat() {
        return CHANNELCHATTYPE.equals(type);
    }

    public Boolean isUserChat() {
        return USERCHATTYPE.equals(type);
    }

    public Boolean isSuperGroupChat() {
        return SUPERGROUPCHATTYPE.equals(type);
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(ID_FIELD, id);
        gen.writeStringField(TYPE_FIELD, type);
        if (isUserChat()) {
            if (firstName != null) {
                gen.writeStringField(FIRSTNAME_FIELD, firstName);
            }
            if (lastName != null) {
                gen.writeStringField(LASTNAME_FIELD, lastName);
            }
        } else {
            if (title != null) {
                gen.writeStringField(TITLE_FIELD, title);
            }
        }
        if (!isGroupChat() && userName != null) {
            gen.writeStringField(USERNAME_FIELD, userName);
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
        return "Chat{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
