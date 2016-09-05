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

package org.telegram.telegrambots.api.methods.groupadministration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.ChatMember;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to get a list of administrators in a chat.
 * An Array of ChatMember objects is returned on success,
 * containing information about all chat administrators except other bots.
 * If the chat is a group or a supergroup and no administrators were appointed,
 * only the creator will be returned.
 * @date 20 of May of 2016
 */
public class GetChatAdministrators extends BotApiMethod<ArrayList<ChatMember>> {
    public static final String PATH = "getChatAdministrators";

    private static final String CHATID_FIELD = "chat_id";
    private String chatId; ///< Unique identifier for the chat to send the message to (Or username for channels)

    public GetChatAdministrators() {
        super();
    }

    public String getChatId() {
        return chatId;
    }

    public GetChatAdministrators setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(CHATID_FIELD, chatId);
        return jsonObject;
    }

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public ArrayList<ChatMember> deserializeResponse(JsonObject answer) {
        if (answer.getBoolean(Constants.RESPONSEFIELDOK)) {
            JsonArray admins = answer.getJsonArray(Constants.RESPONSEFIELDRESULT);
            ArrayList<ChatMember> members = new ArrayList<>();
            for (int i = 0; i < admins.size(); i++) {
                members.add(new ChatMember(admins.getJsonObject(i)));
            }
            return members;
        }
        return null;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(METHOD_FIELD, PATH);
        gen.writeStringField(CHATID_FIELD, chatId);
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

    @Override
    public String toString() {
        return "GetChatAdministrators{" +
                "chatId='" + chatId + '\'' +
                '}';
    }
}
