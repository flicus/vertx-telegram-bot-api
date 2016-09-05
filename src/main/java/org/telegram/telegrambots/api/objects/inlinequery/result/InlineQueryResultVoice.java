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

package org.telegram.telegrambots.api.objects.inlinequery.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represents a link to a voice recording in an .ogg container encoded with OPUS. By default,
 * this voice recording will be sent by the user. Alternatively, you can use input_message_content
 * to send a message with the specified content instead of the the voice message.
 * @note This will only work in Telegram versions released after 9 April, 2016. Older clients will
 * ignore them.
 * @date 10 of April of 2016
 */
public class InlineQueryResultVoice implements InlineQueryResult {

    private static final String TYPE_FIELD = "type";
    @JsonProperty(TYPE_FIELD)
    private static final String type = "voice"; ///< Type of the result, must be "voice"
    private static final String ID_FIELD = "id";
    private static final String VOICEURL_FIELD = "voice_url";
    private static final String TITLE_FIELD = "title";
    private static final String VOICE_DURATION_FIELD = "voice_duration";
    private static final String INPUTMESSAGECONTENT_FIELD = "input_message_content";
    private static final String REPLY_MARKUP_FIELD = "reply_markup";
    @JsonProperty(ID_FIELD)
    private String id; ///< Unique identifier of this result, 1-64 bytes
    @JsonProperty(VOICEURL_FIELD)
    private String voiceUrl; ///< A valid URL for the voice recording
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Recording title
    @JsonProperty(VOICE_DURATION_FIELD)
    private Integer voiceDuration; ///< Optional. Recording duration in seconds
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

    public InlineQueryResultVoice setId(String id) {
        this.id = id;
        return this;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public InlineQueryResultVoice setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultVoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getVoiceDuration() {
        return voiceDuration;
    }

    public InlineQueryResultVoice setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = voiceDuration;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultVoice setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultVoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(TYPE_FIELD, type);
        jsonObject.put(ID_FIELD, this.id);
        jsonObject.put(VOICEURL_FIELD, voiceUrl);
        if (title != null) {
            jsonObject.put(TITLE_FIELD, title);
        }
        if (voiceDuration != null) {
            jsonObject.put(VOICE_DURATION_FIELD, voiceDuration);
        }
        if (replyMarkup != null) {
            jsonObject.put(REPLY_MARKUP_FIELD, replyMarkup.toJson());
        }
        if (inputMessageContent != null) {
            jsonObject.put(INPUTMESSAGECONTENT_FIELD, inputMessageContent.toJson());
        }
        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(TYPE_FIELD, type);
        gen.writeStringField(ID_FIELD, id);
        gen.writeStringField(VOICEURL_FIELD, voiceUrl);
        if (title != null) {
            gen.writeStringField(TITLE_FIELD, title);
        }
        if (voiceDuration != null) {
            gen.writeNumberField(VOICE_DURATION_FIELD, voiceDuration);
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
        return "InlineQueryResultVoice{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", voiceDuration='" + voiceDuration + '\'' +
                ", voiceUrl=" + voiceUrl +
                ", title='" + title + '\'' +
                ", inputMessageContent='" + inputMessageContent + '\'' +
                ", replyMarkup='" + replyMarkup + '\'' +
                '}';
    }
}
