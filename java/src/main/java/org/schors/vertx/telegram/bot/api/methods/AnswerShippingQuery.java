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
import org.schors.vertx.telegram.bot.api.types.payment.ShippingOption;

public class AnswerShippingQuery extends TelegramMethod {

    @JsonProperty("shipping_query_id")
    private String shippingQueryId;
    private Boolean ok;
    @JsonProperty("shipping_options")
    private ShippingOption[] shippingOptions;
    @JsonProperty("error_message")
    private String errorMessage;

    public AnswerShippingQuery() {
    }

    public AnswerShippingQuery(String shippingQueryId, Boolean ok, ShippingOption[] shippingOptions, String errorMessage) {
        this.shippingQueryId = shippingQueryId;
        this.ok = ok;
        this.shippingOptions = shippingOptions;
        this.errorMessage = errorMessage;
    }

    public String getShippingQueryId() {
        return shippingQueryId;
    }

    public AnswerShippingQuery setShippingQueryId(String shippingQueryId) {
        this.shippingQueryId = shippingQueryId;
        return this;
    }

    public Boolean getOk() {
        return ok;
    }

    public AnswerShippingQuery setOk(Boolean ok) {
        this.ok = ok;
        return this;
    }

    public ShippingOption[] getShippingOptions() {
        return shippingOptions;
    }

    public AnswerShippingQuery setShippingOptions(ShippingOption[] shippingOptions) {
        this.shippingOptions = shippingOptions;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AnswerShippingQuery setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    @Override
    public String getMethod() {
        return "answerShippingQuery";
    }
}
