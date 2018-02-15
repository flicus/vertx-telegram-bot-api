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

package org.schors.vertx.telegram.bot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.streams.Pump;
import io.vertx.core.streams.ReadStream;
import org.apache.log4j.Logger;
import org.schors.vertx.telegram.bot.api.types.InputMedia;
import org.schors.vertx.telegram.bot.api.types.keyboard.Markup;

import java.io.File;

public class MultipartHelper {

    private static final Logger log = Logger.getLogger(MultipartHelper.class);

    private HttpClientRequest request;
    private String boundary = Long.toHexString(System.currentTimeMillis());
    private Vertx vertx;
    private ObjectMapper mapper = new ObjectMapper();

    public MultipartHelper(Vertx vertx, HttpClientRequest request) {
        this.request = request;
        this.vertx = vertx;
        request.putHeader("Content-Type", String.format("multipart/form-data; boundary=%s", boundary));
    }

    public MultipartHelper stop() {
        request
                .write("--")
                .write(boundary)
                .write("--")
                .write(System.lineSeparator());
        return this;
    }

    public MultipartHelper putTextBody(String name, String value) {
        if (value != null) {
            request
                    .write("--")
                    .write(boundary)
                    .write(System.lineSeparator())
                    .write(String.format("Content-Disposition: form-data; name=\"%s\"", name))
                    .write(System.lineSeparator())
                    .write(System.lineSeparator())
                    .write(value)
                    .write(System.lineSeparator());
        }
        return this;
    }

    public MultipartHelper putTextBody(String name, Integer value) {
        if (value != null) {
            putTextBody(name, String.valueOf(value));
        }
        return this;
    }

    public MultipartHelper putTextBody(String name, Boolean value) {
        if (value != null) {
            putTextBody(name, String.valueOf(value));
        }
        return this;
    }

    public MultipartHelper putTextBody(String name, Markup value) {
        if (value != null) {
            String replyMarkup = null;
            try {
                replyMarkup = mapper.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                log.error(e, e);
            }
            if (replyMarkup != null)
                putTextBody(name, replyMarkup);
        }
        return this;
    }

    public MultipartHelper putTextBody(String name, InputMedia[] inputMedia) {
        if (inputMedia != null && inputMedia.length > 0) {
            String data = null;
            try {
                data = mapper.writeValueAsString(inputMedia);
            } catch (JsonProcessingException e) {
                log.error(e, e);
            }
            if (data != null) {
                putTextBody(name, data);
            }
        }
        return this;
    }

    public Future putBinaryBody(String name, ReadStream<Buffer> stream, String contentType, String fileName) {
        Future future = Future.future();
        request
                .write("--")
                .write(boundary)
                .write(System.lineSeparator())
                .write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write("Content-Transfer-Encoding: binary")
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        Pump.pump(stream
                .endHandler(event -> {
                    request.write(System.lineSeparator());
                    future.complete();
                })
                .exceptionHandler(e -> future.fail(e)), request)
                .start();
        return future;
    }

    public Future putBinaryBody(String name, File file, String contentType, String fileName) {
        Future future = Future.future();
        request
                .write("--")
                .write(boundary)
                .write(System.lineSeparator())
                .write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write("Content-Transfer-Encoding: binary")
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        vertx.fileSystem().open(file.getPath(), new OpenOptions().setRead(true), ar -> {
            if (ar.succeeded()) {
                Pump.pump(ar.result()
                        .endHandler(event -> {
                            request.write(System.lineSeparator());
                            future.complete();
                        })
                        .exceptionHandler(e -> future.fail(e)), request)
                        .start();
            }
        });
        return future;
    }

    public MultipartHelper putBinaryBody(String name, ReadStream<Buffer> stream, String contentType, String fileName, Handler<AsyncResult> handler) {
        request
                .write("--")
                .write(boundary)
                .write(System.lineSeparator())
                .write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write("Content-Transfer-Encoding: binary")
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        Pump.pump(stream
                .endHandler(event -> {
                    request.write(System.lineSeparator());
                    handler.handle(createResult(true, null));
                })
                .exceptionHandler(e -> handler.handle(createResult(false, e))), request)
                .start();
        return this;
    }

    public MultipartHelper putBinaryBody(String name, File file, String contentType, Handler<AsyncResult> handler) {
        return putBinaryBody(name, file.getAbsolutePath(), contentType, file.getName(), handler);
    }

    public MultipartHelper putBinaryBody(String name, String path, String contentType, String fileName, Handler<AsyncResult> handler) {
        request
                .write("--")
                .write(boundary)
                .write(System.lineSeparator())
                .write(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"", name, fileName))
                .write(System.lineSeparator())
                .write(String.format("Content-Type: %s", contentType))
                .write(System.lineSeparator())
                .write("Content-Transfer-Encoding: binary")
                .write(System.lineSeparator())
                .write(System.lineSeparator());
        vertx.fileSystem().open(path, new OpenOptions().setRead(true), ar -> {
            if (ar.succeeded()) {
                Pump.pump(ar.result()
                        .endHandler(event -> {
                            request.write(System.lineSeparator());
                            handler.handle(createResult(true, null));
                        })
                        .exceptionHandler(e -> handler.handle(createResult(false, e))), request)
                        .start();
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
