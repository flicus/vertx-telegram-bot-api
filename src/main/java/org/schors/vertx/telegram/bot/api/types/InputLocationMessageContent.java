package org.schors.vertx.telegram.bot.api.types;

public class InputLocationMessageContent extends InputMessageContent {

    private Double latitude;
    private Double longitude;

    public InputLocationMessageContent() {
    }

    public InputLocationMessageContent(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public InputLocationMessageContent setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public InputLocationMessageContent setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
