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

public class ShippingAddress {

    @JsonProperty("country_code")
    private String countryCode;
    private String state;
    private String city;
    @JsonProperty("street_line1")
    private String streetLine1;
    @JsonProperty("street_line2")
    private String streetLine2;
    @JsonProperty("post_code")
    private String postCode;

    public ShippingAddress() {
    }

    public ShippingAddress(String countryCode, String state, String city, String streetLine1, String streetLine2, String postCode) {
        this.countryCode = countryCode;
        this.state = state;
        this.city = city;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postCode = postCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public ShippingAddress setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public ShippingAddress setState(String state) {
        this.state = state;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ShippingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public ShippingAddress setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
        return this;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public ShippingAddress setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public ShippingAddress setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
