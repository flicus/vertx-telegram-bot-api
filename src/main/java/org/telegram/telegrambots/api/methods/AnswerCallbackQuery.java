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

package org.telegram.telegrambots.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import io.vertx.core.json.JsonObject;
import org.telegram.telegrambots.Constants;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Use this method to send answers to callback queries sent from inline keyboards. The answer
 * will be displayed to the user as a notification at the top of the chat screen or as an alert. On
 * success, True is returned.
 * @date 10 of April of 2016
 */
public class AnswerCallbackQuery extends BotApiMethod<Boolean> {
    public static final String PATH = "answercallbackquery";

    private static final String CALLBACKQUERYID_FIELD = "callback_query_id";
    private static final String TEXT_FIELD = "text";
    private static final String SHOWALERT_FIELD = "show_alert";

    @JsonProperty(CALLBACKQUERYID_FIELD)
    private String callbackQueryId; ///< Unique identifier for the query to be answered
    @JsonProperty(TEXT_FIELD)
    private String text; ///< Text of the notification. If not specified, nothing will be shown to the user
    @JsonProperty(SHOWALERT_FIELD)
    private Boolean showAlert; ///< Optional. If true, an alert will be shown by the client instead of a notificaiton at the top of the chat screen. Defaults to false.


    public AnswerCallbackQuery() {
        super();
    }

    public String getCallbackQueryId() {
        return this.callbackQueryId;
    }

    public void setCallbackQueryId(String callbackQueryId) {
        this.callbackQueryId = callbackQueryId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getShowAlert() {
        return this.showAlert;
    }

    public void setShowAlert(Boolean showAlert) {
        this.showAlert = showAlert;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(CALLBACKQUERYID_FIELD, callbackQueryId);
        if (text != null) {
            jsonObject.put(TEXT_FIELD, text);
        }
        if (showAlert != null) {
            jsonObject.put(SHOWALERT_FIELD, showAlert);
        }
        return jsonObject;
    }

    @Override
    public String getPath() {
        return PATH;
    }

    @Override
    public Boolean deserializeResponse(JsonObject answer) {
        if (answer.getBoolean(Constants.RESPONSEFIELDOK)) {
            return answer.getBoolean(Constants.RESPONSEFIELDRESULT);
        }
        return null;
    }

    @Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(METHOD_FIELD, PATH);
        gen.writeStringField(CALLBACKQUERYID_FIELD, callbackQueryId);
        if (text != null) {
            gen.writeStringField(TEXT_FIELD, text);
        }
        if (showAlert != null) {
            gen.writeBooleanField(SHOWALERT_FIELD, showAlert);
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
        return "AnswerCallbackQuery{" +
                "callbackQueryId='" + callbackQueryId + '\'' +
                ", text=" + text +
                ", showAlert=" + showAlert + '\'' +
                '}';
    }
}
