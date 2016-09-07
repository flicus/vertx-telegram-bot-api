package org.schors.vertx.telegram;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.Pump;

public class MultipartHelper {

    private HttpClientRequest request;
    private String boundary = Long.toHexString(System.currentTimeMillis());
    private Vertx vertx;

    public MultipartHelper(Vertx vertx, HttpClientRequest request) {
        this.request = request;
        this.vertx = vertx;
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
        request.write(String.format("Content-Disposition: form-data; name=%s\"", name))
                .write(System.lineSeparator())
                .write(System.lineSeparator())
                .write(value)
                .write(System.lineSeparator());
        return this;
    }

    public MultipartHelper putBinaryBody(String name, String path, String contentType, String fileName, Handler<AsyncResult> handler) {
        request.write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        vertx.fileSystem().open(path, new OpenOptions().setRead(true), ar -> {
            if (ar.succeeded()) {
                Pump.pump(new Base64Stream(ar.result())
                        .endHandler(event -> handler.handle(createResult(true, null)))
                        .exceptionHandler(new Handler<Throwable>() {
                            @Override
                            public void handle(Throwable e) {
                                handler.handle(createResult(false, e));
                            }
                        }), request).start();
            }
        });
        return this;
    }

    private AsyncResult createResult(boolean succeed, Throwable e) {
        return new AsyncResult() {
            @Override
            public Object result() {
                return null;
            }

            @Override
            public Throwable cause() {
                return e;
            }

            @Override
            public boolean succeeded() {
                return succeed;
            }

            @Override
            public boolean failed() {
                return !succeed;
            }
        };
    }

}
