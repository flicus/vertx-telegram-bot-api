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

package org.telegram.telegrambots.api.objects.inlinequery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;
import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.User;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represents a result of an inline query that was chosen by the user and sent to their chat
 * partner.
 * @date 01 of January of 2016
 */
public class ChosenInlineQuery implements IBotApiObject {
    private static final String RESULTID_FIELD = "result_id";
    private static final String FROM_FIELD = "from";
    private static final String LOCATION_FIELD = "location";
    private static final String INLINE_MESSAGE_ID_FIELD = "inline_message_id";
    private static final String QUERY_FIELD = "query";

    @JsonProperty(RESULTID_FIELD)
    private String resultId; ///< The unique identifier for the result that was chosen.
    @JsonProperty(FROM_FIELD)
    private User from; ///< The user that chose the result.
    @JsonProperty(LOCATION_FIELD)
    private Location location; ///< Optional. Sender location, only for bots that require user location
    @JsonProperty(INLINE_MESSAGE_ID_FIELD)
    /**
     * Optional.
     * Identifier of the sent inline message.
     * Available only if there is an inline keyboard attached to the message.
     * Will be also received in callback queries and can be used to edit the message.
     */
    private String inlineMessageId;
    @JsonProperty(QUERY_FIELD)
    private String query; ///< The query that was used to obtain the result.

    public ChosenInlineQuery() {
        super();
    }

    public ChosenInlineQuery(JsonObject jsonObject) {
        super();
        this.resultId = jsonObject.getString(RESULTID_FIELD);
        this.from = new User(jsonObject.getJsonObject(FROM_FIELD));
        if (jsonObject.containsKey(LOCATION_FIELD)) {
            location = new Location(jsonObject.getJsonObject(LOCATION_FIELD));
        }
        if (jsonObject.containsKey(INLINE_MESSAGE_ID_FIELD)) {
            inlineMessageId = jsonObject.getString(INLINE_MESSAGE_ID_FIELD);
        }
        this.query = jsonObject.getString(QUERY_FIELD);
    }

    public String getResultId() {
        return resultId;
    }

    public User getFrom() {
        return from;
    }

    public Location getLocation() {
        return location;
    }

    public String getInlineMessageId() {
        return inlineMessageId;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(RESULTID_FIELD, resultId);
        gen.writeObjectField(FROM_FIELD, from);
        if (location != null) {
            gen.writeObjectField(LOCATION_FIELD, location);
        }
        if (inlineMessageId != null) {
            gen.writeStringField(INLINE_MESSAGE_ID_FIELD, inlineMessageId);
        }
        gen.writeStringField(QUERY_FIELD, query);
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "ChosenInlineQuery{" +
                "resultId='" + resultId + '\'' +
                ", from=" + from +
                ", location=" + location +
                ", inlineMessageId=" + inlineMessageId +
                ", query='" + query + '\'' +
                '}';
    }
}
