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

public class SuccessfulPayment {

    private String currency;
    @JsonProperty("total_amount")
    private Integer totalAmount;
    @JsonProperty("invoice_payload")
    private String invoicePayload;
    @JsonProperty("shipping_option_id")
    private String shippingOptionId;
    @JsonProperty("order_info")
    private OrderInfo orderInfo;
    @JsonProperty("telegram_payment_charge_id")
    private String telegramPaymentChargeId;
    @JsonProperty("provider_payment_charge_id")
    private String providerPaymentChargeId;

    public SuccessfulPayment() {
    }

    public SuccessfulPayment(String currency, Integer totalAmount, String invoicePayload, String shippingOptionId, OrderInfo orderInfo, String telegramPaymentChargeId, String providerPaymentChargeId) {
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.invoicePayload = invoicePayload;
        this.shippingOptionId = shippingOptionId;
        this.orderInfo = orderInfo;
        this.telegramPaymentChargeId = telegramPaymentChargeId;
        this.providerPaymentChargeId = providerPaymentChargeId;
    }

    public String getCurrency() {
        return currency;
    }

    public SuccessfulPayment setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public SuccessfulPayment setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getInvoicePayload() {
        return invoicePayload;
    }

    public SuccessfulPayment setInvoicePayload(String invoicePayload) {
        this.invoicePayload = invoicePayload;
        return this;
    }

    public String getShippingOptionId() {
        return shippingOptionId;
    }

    public SuccessfulPayment setShippingOptionId(String shippingOptionId) {
        this.shippingOptionId = shippingOptionId;
        return this;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public SuccessfulPayment setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
        return this;
    }

    public String getTelegramPaymentChargeId() {
        return telegramPaymentChargeId;
    }

    public SuccessfulPayment setTelegramPaymentChargeId(String telegramPaymentChargeId) {
        this.telegramPaymentChargeId = telegramPaymentChargeId;
        return this;
    }

    public String getProviderPaymentChargeId() {
        return providerPaymentChargeId;
    }

    public SuccessfulPayment setProviderPaymentChargeId(String providerPaymentChargeId) {
        this.providerPaymentChargeId = providerPaymentChargeId;
        return this;
    }
}
