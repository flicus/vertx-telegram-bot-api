package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatPhoto {

    @JsonProperty("small_file_id")
    private String smallFileId;
    @JsonProperty("big_file_id")
    private String bigFileId;

    public ChatPhoto() {
    }

    public ChatPhoto(String smallFileId, String bigFileId) {
        this.smallFileId = smallFileId;
        this.bigFileId = bigFileId;
    }

    public String getSmallFileId() {
        return smallFileId;
    }

    public ChatPhoto setSmallFileId(String smallFileId) {
        this.smallFileId = smallFileId;
        return this;
    }

    public String getBigFileId() {
        return bigFileId;
    }

    public ChatPhoto setBigFileId(String bigFileId) {
        this.bigFileId = bigFileId;
        return this;
    }
}
