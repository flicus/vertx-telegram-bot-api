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
 * @brief Represents a link to an mp3 audio file. By default, this audio file will be sent by the
 * user. Alternatively, you can use input_message_content to send a message with the specified
 * content instead of the audio.
 * @note This will only work in Telegram versions released after 9 April, 2016. Older clients will
 * ignore them.
 * @date 10 of April of 2016
 */
public class InlineQueryResultAudio implements InlineQueryResult {

    private static final String TYPE_FIELD = "type";
    @JsonProperty(TYPE_FIELD)
    private static final String type = "audio"; ///< Type of the result, must be "audio"
    private static final String ID_FIELD = "id";
    private static final String AUDIOURL_FIELD = "audio_url";
    private static final String TITLE_FIELD = "title";
    private static final String PERFORMER_FIELD = "performer";
    private static final String AUDIO_DURATION_FIELD = "audio_duration";
    private static final String INPUTMESSAGECONTENT_FIELD = "input_message_content";
    private static final String REPLY_MARKUP_FIELD = "reply_markup";
    @JsonProperty(ID_FIELD)
    private String id; ///< Unique identifier of this result
    @JsonProperty(AUDIOURL_FIELD)
    private String audioUrl; ///< A valid URL for the audio file
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Optional. Title for the result
    @JsonProperty(PERFORMER_FIELD)
    private String performer; ///< Optional. Performer
    @JsonProperty(AUDIO_DURATION_FIELD)
    private Integer audioDuration; ///< Optional. Audio duration in seconds
    @JsonProperty(INPUTMESSAGECONTENT_FIELD)
    private InputMessageContent inputMessageContent; ///< Optional. Content of the message to be sent instead of the audio
    @JsonProperty(REPLY_MARKUP_FIELD)
    private InlineKeyboardMarkup replyMarkup; ///< Optional. Inline keyboard attached to the message

    public static String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public InlineQueryResultAudio setId(String id) {
        this.id = id;
        return this;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public InlineQueryResultAudio setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InlineQueryResultAudio setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public InlineQueryResultAudio setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public Integer getAudioDuration() {
        return audioDuration;
    }

    public InlineQueryResultAudio setAudioDuration(Integer audioDuration) {
        this.audioDuration = audioDuration;
        return this;
    }

    public InputMessageContent getInputMessageContent() {
        return inputMessageContent;
    }

    public InlineQueryResultAudio setInputMessageContent(InputMessageContent inputMessageContent) {
        this.inputMessageContent = inputMessageContent;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public InlineQueryResultAudio setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.put(TYPE_FIELD, type);
        jsonObject.put(ID_FIELD, id);
        jsonObject.put(AUDIOURL_FIELD, audioUrl);
        if (title != null) {
            jsonObject.put(TITLE_FIELD, title);
        }
        if (performer != null) {
            jsonObject.put(PERFORMER_FIELD, performer);
        }
        if (audioDuration != null) {
            jsonObject.put(AUDIO_DURATION_FIELD, audioDuration);
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
        gen.writeStringField(AUDIOURL_FIELD, audioUrl);
        if (title != null) {
            gen.writeStringField(TITLE_FIELD, title);
        }
        if (performer != null) {
            gen.writeStringField(PERFORMER_FIELD, performer);
        }
        if (replyMarkup != null) {
            gen.writeObjectField(REPLY_MARKUP_FIELD, replyMarkup);
        }
        if (inputMessageContent != null) {
            gen.writeObjectField(INPUTMESSAGECONTENT_FIELD, inputMessageContent);
        }
        if (audioDuration != null) {
            gen.writeNumberField(AUDIO_DURATION_FIELD, audioDuration);
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
        return "InlineQueryResultAudio{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", title='" + title + '\'' +
                ", performer=" + performer +
                ", audioDuration=" + audioDuration +
                ", inputMessageContent='" + inputMessageContent + '\'' +
                ", replyMarkup='" + replyMarkup + '\'' +
                '}';
    }
}
