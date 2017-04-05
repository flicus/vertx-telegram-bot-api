package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputContactMessageContent extends InputMessageContent {

    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public InputContactMessageContent() {
    }

    public InputContactMessageContent(String phoneNumber, String firstName, String lastName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public InputContactMessageContent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public InputContactMessageContent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public InputContactMessageContent setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
