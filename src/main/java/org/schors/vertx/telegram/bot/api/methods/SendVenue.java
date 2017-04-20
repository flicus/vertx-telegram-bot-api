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
import org.schors.vertx.telegram.bot.api.types.Markup;

public class SendVenue extends TelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;
    private Double latitude;
    private Double longitude;
    private String title;
    private String address;
    @JsonProperty("foursquare_id")
    private String foursquareId;
    @JsonProperty("disable_notification")
    private Boolean disableNotification;
    @JsonProperty("reply_to_message_id")
    private Integer replyToMessageId;
    @JsonProperty("reply_markup")
    private Markup replyMarkup;

    public SendVenue() {
    }

    public SendVenue(String chatId, Double latitude, Double longitude, String title, String address, String foursquareId, Boolean disableNotification, Integer replyToMessageId, Markup replyMarkup) {
        this.chatId = chatId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    public String getChatId() {
        return chatId;
    }

    public SendVenue setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public SendVenue setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public SendVenue setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SendVenue setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SendVenue setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public SendVenue setFoursquareId(String foursquareId) {
        this.foursquareId = foursquareId;
        return this;
    }

    public Boolean getDisableNotification() {
        return disableNotification;
    }

    public SendVenue setDisableNotification(Boolean disableNotification) {
        this.disableNotification = disableNotification;
        return this;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }

    public SendVenue setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = replyToMessageId;
        return this;
    }

    public Markup getReplyMarkup() {
        return replyMarkup;
    }

    public SendVenue setReplyMarkup(Markup replyMarkup) {
        this.replyMarkup = replyMarkup;
        return this;
    }

    @Override
    public String getMethod() {
        return "sendVenue";
    }
}
