package org.schors.vertx.telegram;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.streams.ReadStream;

import java.util.Base64;

public class Base64Stream implements ReadStream<Buffer> {

    public static final int DEFAULT_READ_BUFFER_SIZE = 8192;
    private static final Logger log = LoggerFactory.getLogger(Base64Stream.class);
    private Vertx vertx;
    private AsyncFile asyncFile;
    private Base64.Encoder encoder = Base64.getMimeEncoder(80, System.lineSeparator().getBytes());

    private Handler<Throwable> exceptionHandler;
    private Handler<Buffer> dataHandler;
    private Handler<Void> endHandler;

    private boolean closed;
    private boolean paused;
    private int readBufferSize = DEFAULT_READ_BUFFER_SIZE;
    private boolean readInProgress;
    private long readPos;

    public Base64Stream(Vertx vertx, String path) {
        this.vertx = vertx;
        vertx.fileSystem().open(path, new OpenOptions().setRead(true), result -> {
            if (result.succeeded()) {
                asyncFile = result.result();
            }
        });
    }

    @Override
    public ReadStream exceptionHandler(Handler<Throwable> handler) {
        check();
        this.exceptionHandler = handler;
        return this;
    }

    @Override
    public ReadStream handler(Handler<Buffer> handler) {
        check();
        this.dataHandler = handler;
        if (dataHandler != null && !paused && !closed) {
            doRead();
        }
        return this;
    }

    @Override
    public ReadStream pause() {
        check();
        paused = true;
        return this;
    }

    @Override
    public ReadStream resume() {
        check();
        if (paused && !closed) {
            paused = false;
            if (dataHandler != null) {
                doRead();
            }
        }
        return this;
    }

    @Override
    public ReadStream endHandler(Handler<Void> endHandler) {
        check();
        this.endHandler = endHandler;
        return this;
    }

    private synchronized void doRead() {
        if (!readInProgress) {
            readInProgress = true;
            Buffer buff = Buffer.buffer(readBufferSize);
            asyncFile.read(buff, 0, readPos, readBufferSize, ar -> {
                if (ar.succeeded()) {
                    readInProgress = false;
                    Buffer buffer = ar.result();
                    if (buffer.length() == 0) {
                        handleEnd();
                    } else {
                        readPos += buffer.length();
                        //base64 encode data from buffer
                        handleData(Buffer.buffer(encoder.encode(buffer.getBytes())));
                        if (!paused && dataHandler != null) {
                            doRead();
                        }
                    }
                } else {
                    handleException(ar.cause());
                }
            });
        }
    }

    private void check() {
        checkClosed();
    }

    private void checkClosed() {
        if (closed) {
            throw new IllegalStateException("File handle is closed");
        }
    }

    private synchronized void handleData(Buffer buffer) {
        if (dataHandler != null) {
//            checkContext();
            dataHandler.handle(buffer);
        }
    }

    private synchronized void handleEnd() {
        closed = true;
        asyncFile.close();
        if (endHandler != null) {
//            checkContext();
            endHandler.handle(null);
        }
    }

    private void handleException(Throwable t) {
        if (exceptionHandler != null && t instanceof Exception) {
            exceptionHandler.handle(t);
        } else {
            log.error("Unhandled exception", t);
        }
    }
}
