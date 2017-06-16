/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2017 schors
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

package org.schors.vertx.telegram.bot.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnswerPreCheckoutQuery extends TelegramMethod {

    @JsonProperty("pre_checkout_query_id")
    private String preCheckoutQueryId;
    private Boolean ok;
    @JsonProperty("error_message")
    private String errorMessage;

    public AnswerPreCheckoutQuery() {
    }

    public AnswerPreCheckoutQuery(String preCheckoutQueryId, Boolean ok, String errorMessage) {
        this.preCheckoutQueryId = preCheckoutQueryId;
        this.ok = ok;
        this.errorMessage = errorMessage;
    }

    public String getPreCheckoutQueryId() {
        return preCheckoutQueryId;
    }

    public AnswerPreCheckoutQuery setPreCheckoutQueryId(String preCheckoutQueryId) {
        this.preCheckoutQueryId = preCheckoutQueryId;
        return this;
    }

    public Boolean getOk() {
        return ok;
    }

    public AnswerPreCheckoutQuery setOk(Boolean ok) {
        this.ok = ok;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AnswerPreCheckoutQuery setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String getMethod() {
        return "answerPreCheckoutQuery";
    }
}
