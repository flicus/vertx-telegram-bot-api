package org.schors.vertx.telegram.bot.api.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

import java.io.File;

public class InputMedia {

    @JsonIgnore
    protected String mediaName;
    @JsonIgnore
    protected File file;
    @JsonIgnore
    protected ReadStream<Buffer> stream;
    @JsonIgnore
    protected String localFilePath;

    protected String media;
    protected String caption;

    public InputMedia() {
    }

    public InputMedia(String media, String caption) {
        this.media = media;
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public InputMedia setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getMediaName() {
        return mediaName;
    }

    public InputMedia setMediaName(String mediaName) {
        this.mediaName = mediaName;
        return this;
    }

    public String getMedia() {
        return media;
    }

    public InputMedia setMedia(String media) {
        this.media = media;
        return this;
    }

    public InputMedia setMedia(File file, String filename) {
        this.file = file;
        this.mediaName = filename;
        this.media = "attach://" + filename;
        return this;
    }

    public InputMedia setMedia(ReadStream<Buffer> stream, String filename) {
        this.stream = stream;
        this.mediaName = filename;
        this.media = "attach://" + filename;
        return this;
    }

    public File getFile() {
        return file;
    }

    public ReadStream<Buffer> getStream() {
        return stream;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public InputMedia setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
        return this;
    }

    public boolean hasBinary() {
        return this.file != null || this.stream != null;
    }
}
