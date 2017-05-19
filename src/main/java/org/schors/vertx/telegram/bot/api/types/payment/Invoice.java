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

package org.schors.vertx.telegram.bot.api.types.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {

    private String title;
    private String description;
    @JsonProperty("start_parameter")
    private String startParameter;
    private String currency;
    @JsonProperty("total_amount")
    private Integer totalAmount;

    public Invoice() {
    }

    public Invoice(String title, String description, String startParameter, String currency, Integer totalAmount) {
        this.title = title;
        this.description = description;
        this.startParameter = startParameter;
        this.currency = currency;
        this.totalAmount = totalAmount;
    }

    public String getTitle() {
        return title;
    }

    public Invoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Invoice setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStartParameter() {
        return startParameter;
    }

    public Invoice setStartParameter(String startParameter) {
        this.startParameter = startParameter;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Invoice setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public Invoice setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }
}
