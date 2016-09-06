package org.schors.vertx.telegram;

import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.Pump;

import java.io.File;

public class MultipartHelper {

    private HttpClientRequest request;
    private String boundary = Long.toHexString(System.currentTimeMillis());

    public MultipartHelper(HttpClientRequest request) {
        this.request = request;
        request.putHeader("Content-Type", String.format("multipart/form-data; boundary=%s", boundary));
    }

    public MultipartHelper start() {
        request
                .write(System.lineSeparator())
                .write(System.lineSeparator())
                .write("--")
                .write(boundary)
                .write(System.lineSeparator());
        return this;
    }

    public MultipartHelper stop() {
        request.write(System.lineSeparator());
        return this;
    }

    public MultipartHelper putTextBody(String name, String value) {
        request.write("Content-Disposition: form-data; name=\"")
                .write(name)
                .write(System.lineSeparator())
                .write(System.lineSeparator())
                .write(value)
                .write(System.lineSeparator());
        return this;
    }

    public MultipartHelper putBinaryBody(String name, File data, String contentType, String fileName) {
        request.write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        Pump.pump(new Base64Stream(data).endHandler(event -> {

        }), request).start();

        return this;
    }


}
