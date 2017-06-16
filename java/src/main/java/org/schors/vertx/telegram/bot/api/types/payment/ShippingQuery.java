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
import org.schors.vertx.telegram.bot.api.types.User;

public class ShippingQuery {

    private String id;
    private User from;
    @JsonProperty("invoice_payload")
    private String invoicePayload;
    @JsonProperty("shipping_address")
    private ShippingAddress shippingAddress;

    public ShippingQuery() {
    }

    public ShippingQuery(String id, User from, String invoicePayload, ShippingAddress shippingAddress) {
        this.id = id;
        this.from = from;
        this.invoicePayload = invoicePayload;
        this.shippingAddress = shippingAddress;
    }

    public String getId() {
        return id;
    }

    public ShippingQuery setId(String id) {
        this.id = id;
        return this;
    }

    public User getFrom() {
        return from;
    }

    public ShippingQuery setFrom(User from) {
        this.from = from;
        return this;
    }

    public String getInvoicePayload() {
        return invoicePayload;
    }

    public ShippingQuery setInvoicePayload(String invoicePayload) {
        this.invoicePayload = invoicePayload;
        return this;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public ShippingQuery setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }
}
