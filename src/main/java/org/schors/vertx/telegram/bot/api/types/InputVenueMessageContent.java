package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputVenueMessageContent extends InputMessageContent {

    private Double latitude;
    private Double longitude;
    private String title;
    private String address;
    @JsonProperty("foursquare_id")
    private String foursquareId;

    public InputVenueMessageContent() {
    }

    public InputVenueMessageContent(Double latitude, Double longitude, String title, String address, String foursquareId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.address = address;
        this.foursquareId = foursquareId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public InputVenueMessageContent setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public InputVenueMessageContent setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public InputVenueMessageContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public InputVenueMessageContent setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFoursquareId() {
        return foursquareId;
    }

    public InputVenueMessageContent setFoursquareId(String foursquareId) {
        this.foursquareId = foursquareId;
        return this;
    }
}
