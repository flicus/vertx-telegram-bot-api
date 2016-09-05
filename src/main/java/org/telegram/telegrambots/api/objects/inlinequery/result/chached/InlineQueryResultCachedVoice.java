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

package org.telegram.telegrambots.api.objects.inlinequery.result.chached;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represents a link to a voice message stored on the Telegram servers. By default, this
 * voice message will be sent by the user. Alternatively, you can use input_message_content to send
 * a message with the specified content instead of the voice message.
 * @note This will only work in Telegram versions released after 9 April, 2016. Older clients will
 * ignore them.
 * @date 10 of April of 2016
 */
public class InlineQueryResultCachedVoice implements InlineQueryResult {

    private static final String TYPE_FIELD = "type";
    @JsonProperty(TYPE_FIELD)
    private static final String type = "voice"; ///< Type of the result, must be "voice"
    private static final String ID_FIELD = "id";
    private static final String VOICE_FILE_ID_FIELD = "voice_file_id";
    private static final String TITLE_FIELD = "title";
    private static final String INPUTMESSAGECONTENT_FIELD = "input_message_content";
    private static final String REPLY_MARKUP_FIELD = "reply_markup";
    @JsonProperty(ID_FIELD)
    private String id; ///< Unique identifier of this result, 1-64 bytes
    @JsonProperty(VOICE_FILE_ID_FIELD)
    private String voiceFileId; ///< A valid file identifier for the voice message
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Recording title
    @JsonProperty(INPUTMESSAGECONTENT_FIELD)
    private InputMessageContent inputMessageContent; ///< Optional. Content of the message to be sent instead of the voice recording
    @JsonProperty(REPLY_MARKUP_FIELD)
    private InlineKeyboardMarkup replyMarkup; ///< Optional. Inline keyboard attached to the message

    public static String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultCachedVoice setId(String id) {
        this.id = id;
        return this;
    }

    public String getVoiceFileId() {
        return voiceFileId;
    }

    public InlineQueryResultCachedVoice setVoiceFileId(String voiceFileId) {
        this.voiceFileId = voiceFileId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultCachedVoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultCachedVoice setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultCachedVoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(TYPE_FIELD, type);
        jsonObject.put(ID_FIELD, this.id);
        jsonObject.put(VOICE_FILE_ID_FIELD, voiceFileId);
        if (title != null) {
            jsonObject.put(TITLE_FIELD, this.title);
        }
        if (replyMarkup != null) {
            jsonObject.put(REPLY_MARKUP_FIELD, replyMarkup.toJson());
        }
        if (inputMessageContent != null) {
            jsonObject.put(INPUTMESSAGECONTENT_FIELD, inputMessageContent);
        }
        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(TYPE_FIELD, type);
        gen.writeStringField(ID_FIELD, id);
        gen.writeStringField(VOICE_FILE_ID_FIELD, voiceFileId);
        if (title != null) {
            gen.writeStringField(TITLE_FIELD, this.title);
        }
        if (replyMarkup != null) {
            gen.writeObjectField(REPLY_MARKUP_FIELD, replyMarkup);
        }
        if (inputMessageContent != null) {
            gen.writeObjectField(INPUTMESSAGECONTENT_FIELD, inputMessageContent);
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
        return "InlineQueryResultCachedVoice{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", voiceFileId='" + voiceFileId + '\'' +
                ", title='" + title + '\'' +
                ", inputMessageContent='" + inputMessageContent + '\'' +
                ", replyMarkup='" + replyMarkup + '\'' +
                '}';
    }
}
