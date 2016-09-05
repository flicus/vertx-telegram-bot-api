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
 * @brief This object represents one special entity in a text message. For example, hashtags,
 * usernames, URL.
 * @date 20 of June of 2015
 */
public class MessageEntity implements IBotApiObject {

    private static final String TYPE_FIELD = "type";
    private static final String OFFSET_FIELD = "offset";
    private static final String LENGTH_FIELD = "length";
    private static final String URL_FIELD = "url";
    private static final String USER_FIELD = "user";
    @JsonProperty(TYPE_FIELD)
    /**
     * Type of the entity. One of
     * mention (@username),
     * hashtag,
     * bot_command,
     * url,
     * email,
     * bold (bold text),
     * italic (italic text),
     * code (monowidth string),
     * pre (monowidth block),
     * text_link (for clickable text URLs)
     * text_mention (for users without usernames)
     */
    private String type;
    @JsonProperty(OFFSET_FIELD)
    private Integer offset; ///< Offset in UTF-16 code units to the start of the entity
    @JsonProperty(LENGTH_FIELD)
    private Integer length; ///< Length of the entity in UTF-16 code units
    @JsonProperty(URL_FIELD)
    private String url; ///< Optional. For “text_link” only, url that will be opened after user taps on the text
    @JsonProperty(USER_FIELD)
    private User user; ///< Optional. For “text_mention” only, the mentioned user

    private String text; ///< Text present in the entity. Computed from offset and length

    public MessageEntity() {
        super();
    }

    public MessageEntity(JsonObject jsonObject) {
        super();
        this.type = jsonObject.getString(TYPE_FIELD);
        this.offset = jsonObject.getInteger(OFFSET_FIELD);
        this.length = jsonObject.getInteger(LENGTH_FIELD);
        if (EntityType.TEXTLINK.equals(type)) {
            this.url = jsonObject.getString(URL_FIELD);
        }
        if (EntityType.TEXTMENTION.equals(type)) {
            this.user = new User(jsonObject.getJsonObject(USER_FIELD));
        }
    }

    public String getType() {
        return type;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLength() {
        return length;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    protected void computeText(String message) {
        text = message.substring(offset, offset + length);
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(TYPE_FIELD, type);
        gen.writeNumberField(OFFSET_FIELD, offset);
        gen.writeNumberField(LENGTH_FIELD, length);
        if (url != null && EntityType.TEXTLINK.equals(type)) {
            gen.writeStringField(URL_FIELD, url);
        }
        if (user != null && EntityType.TEXTMENTION.equals(type)) {
            gen.writeObjectField(USER_FIELD, user);
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
        return "MessageEntity{" +
                "type='" + type + '\'' +
                ", offset=" + offset +
                ", length=" + length +
                ", url=" + url +
                ", user=" + user +
                '}';
    }
}
