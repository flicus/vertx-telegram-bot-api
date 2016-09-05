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

package org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.api.methods.ParseMode;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Represents the content of a text message to be sent as the result of an inline query.
 * @date 10 of April of 2016
 */
public class InputTextMessageContent implements InputMessageContent {

    private static final String MESSAGETEXT_FIELD = "message_text";
    private static final String PARSEMODE_FIELD = "parse_mode";
    private static final String DISABLEWEBPAGEPREVIEW_FIELD = "disable_web_page_preview";
    @JsonProperty(MESSAGETEXT_FIELD)
    private String messageText; ///< Text of a message to be sent, 1-4096 characters
    @JsonProperty(PARSEMODE_FIELD)
    private String parseMode; ///< Optional. Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in your bot's message.
    @JsonProperty(DISABLEWEBPAGEPREVIEW_FIELD)
    private Boolean disableWebPagePreview; ///< Optional. Disables link previews for links in the sent message

    public InputTextMessageContent() {
        super();
    }

    public String getMessageText() {
        return messageText;
    }

    public InputTextMessageContent setMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public String getParseMode() {
        return parseMode;
    }

    public InputTextMessageContent setParseMode(String parseMode) {
        this.parseMode = parseMode;
        return this;
    }

    public Boolean getDisableWebPagePreview() {
        return disableWebPagePreview;
    }

    public InputTextMessageContent setDisableWebPagePreview(Boolean disableWebPagePreview) {
        this.disableWebPagePreview = disableWebPagePreview;
        return this;
    }

    public InputTextMessageContent enableMarkdown(boolean enable) {
        if (enable) {
            this.parseMode = ParseMode.MARKDOWN;
        } else {
            this.parseMode = null;
        }
        return this;
    }

    public InputTextMessageContent enableHtml(boolean enable) {
        if (enable) {
            this.parseMode = ParseMode.HTML;
        } else {
            this.parseMode = null;
        }
        return this;
    }

    public InputTextMessageContent disableWebPagePreview() {
        disableWebPagePreview = true;
        return this;
    }

    public InputTextMessageContent enableWebPagePreview() {
        disableWebPagePreview = null;
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(MESSAGETEXT_FIELD, this.messageText);
        if (parseMode != null) {
            jsonObject.put(PARSEMODE_FIELD, this.parseMode);
        }
        if (disableWebPagePreview != null) {
            jsonObject.put(DISABLEWEBPAGEPREVIEW_FIELD, this.disableWebPagePreview);
        }

        return jsonObject;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(MESSAGETEXT_FIELD, messageText);
        if (parseMode != null) {
            gen.writeStringField(PARSEMODE_FIELD, this.parseMode);
        }
        if (disableWebPagePreview != null) {
            gen.writeBooleanField(DISABLEWEBPAGEPREVIEW_FIELD, this.disableWebPagePreview);
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
        return "InputTextMessageContent{" +
                ", messageText='" + messageText + '\'' +
                ", parseMode='" + parseMode + '\'' +
                ", disableWebPagePreview=" + disableWebPagePreview +
                '}';
    }
}
