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
 * @brief Upon receiving a message with this object, Telegram clients will display a reply interface
 * to the user (act as if the user has selected the bot‘s message and tapped ’Reply'). This can be
 * extremely useful if you want to create user-friendly step-by-step interfaces without having to
 * sacrifice privacy mode.
 * @date 22 of June of 2015
 */
public class ForceReplyKeyboard implements ReplyKeyboard {
    private static final String FORCEREPLY_FIELD = "force_reply";
    private static final String SELECTIVE_FIELD = "selective";
    /**
     * Shows reply interface to the user, as if they manually selected the bot‘s message and tapped
     * ’Reply'
     */
    @JsonProperty(FORCEREPLY_FIELD)
    private Boolean forceReply;
    /**
     * Use this parameter if you want to force reply from specific users only. Targets: 1) users
     * that are @mentioned in the text of the Message object; 2) if the bot's message is a reply
     * (has reply_to_message_id), sender of the original message.
     */
    @JsonProperty(SELECTIVE_FIELD)
    private Boolean selective;

    public ForceReplyKeyboard() {
        super();
        this.forceReply = true;
    }

    public ForceReplyKeyboard(JsonObject jsonObject) {
        super();
        if (jsonObject.containsKey(FORCEREPLY_FIELD)) {
            this.forceReply = jsonObject.getBoolean(FORCEREPLY_FIELD);
        }
        if (jsonObject.containsKey(SELECTIVE_FIELD)) {
            this.selective = jsonObject.getBoolean(SELECTIVE_FIELD);
        }
    }

    public Boolean getForceReply() {
        return forceReply;
    }

    public Boolean getSelective() {
        return selective;
    }

    public ForceReplyKeyboard setSelective(Boolean selective) {
        this.selective = selective;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.put(FORCEREPLY_FIELD, this.forceReply);
        if (this.selective != null) {
            jsonObject.put(SELECTIVE_FIELD, this.selective);
        }

        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField(FORCEREPLY_FIELD, forceReply);
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
        return "ForceReplyKeyboard{" +
                "forceReply=" + forceReply +
                ", selective=" + selective +
                '}';
    }
}
