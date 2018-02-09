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
import org.schors.vertx.telegram.bot.api.types.inline.InlineKeyboardMarkup;
import org.schors.vertx.telegram.bot.api.types.payment.LabeledPrice;

public class SendInvoice extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private String title;
    private String description;
    private String payload;
    @JsonProperty("provider_token")
    private String providerToken;
    @JsonProperty("start_parameter")
    private String startParameter;
    private String currency;
    private LabeledPrice[] prices;
    @JsonProperty("photo_url")
    private String photoUrl;
    @JsonProperty("photo_size")
    private Integer photoSize;
    @JsonProperty("photo_width")
    private Integer photoWidth;
    @JsonProperty("photo_height")
    private Integer photoHeight;
    @JsonProperty("need_name")
    private Boolean needName;
    @JsonProperty("need_phone_number")
    private Boolean needPhoneNumber;
    @JsonProperty("need_email")
    private Boolean needEmail;
    @JsonProperty("need_shipping_address")
    private Boolean needShippingAddress;
    @JsonProperty("is_flexible")
    private Boolean isFlexible;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private InlineKeyboardMarkup replyMarkup;
    @JsonProperty("provider_data")
    private String providerData;
    @JsonProperty("send_phone_number_to_provider")
    private Boolean sendPhoneNumberToProvider;
    @JsonProperty("send_email_to_provider")
    private Boolean sendEmailToProvider;


    public SendInvoice() {
    }

    public String getChatId() {
        return chatId;
    }

    public SendInvoice setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SendInvoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SendInvoice setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPayload() {
        return payload;
    }

    public SendInvoice setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public SendInvoice setProviderToken(String providerToken) {
        this.providerToken = providerToken;
        return this;
    }

    public String getStartParameter() {
        return startParameter;
    }

    public SendInvoice setStartParameter(String startParameter) {
        this.startParameter = startParameter;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public SendInvoice setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public LabeledPrice[] getPrices() {
        return prices;
    }

    public SendInvoice setPrices(LabeledPrice[] prices) {
        this.prices = prices;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public SendInvoice setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public Integer getPhotoSize() {
        return photoSize;
    }

    public SendInvoice setPhotoSize(Integer photoSize) {
        this.photoSize = photoSize;
        return this;
    }

    public Integer getPhotoWidth() {
        return photoWidth;
    }

    public SendInvoice setPhotoWidth(Integer photoWidth) {
        this.photoWidth = photoWidth;
        return this;
    }

    public Integer getPhotoHeight() {
        return photoHeight;
    }

    public SendInvoice setPhotoHeight(Integer photoHeight) {
        this.photoHeight = photoHeight;
        return this;
    }

    public Boolean getNeedName() {
        return needName;
    }

    public SendInvoice setNeedName(Boolean needName) {
        this.needName = needName;
        return this;
    }

    public Boolean getNeedPhoneNumber() {
        return needPhoneNumber;
    }

    public SendInvoice setNeedPhoneNumber(Boolean needPhoneNumber) {
        this.needPhoneNumber = needPhoneNumber;
        return this;
    }

    public Boolean getNeedEmail() {
        return needEmail;
    }

    public SendInvoice setNeedEmail(Boolean needEmail) {
        this.needEmail = needEmail;
        return this;
    }

    public Boolean getNeedShippingAddress() {
        return needShippingAddress;
    }

    public SendInvoice setNeedShippingAddress(Boolean needShippingAddress) {
        this.needShippingAddress = needShippingAddress;
        return this;
    }

    public Boolean getFlexible() {
        return isFlexible;
    }

    public SendInvoice setFlexible(Boolean flexible) {
        isFlexible = flexible;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public SendInvoice setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendInvoice setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public InlineKeyboardMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public SendInvoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    public String getProviderData() {
        return providerData;
    }

    public SendInvoice setProviderData(String providerData) {
        this.providerData = providerData;
        return this;
    }

    public Boolean getSendPhoneNumberToProvider() {
        return sendPhoneNumberToProvider;
    }

    public SendInvoice setSendPhoneNumberToProvider(Boolean sendPhoneNumberToProvider) {
        this.sendPhoneNumberToProvider = sendPhoneNumberToProvider;
        return this;
    }

    public Boolean getSendEmailToProvider() {
        return sendEmailToProvider;
    }

    public SendInvoice setSendEmailToProvider(Boolean sendEmailToProvider) {
        this.sendEmailToProvider = sendEmailToProvider;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendInvoice";
    }
}
