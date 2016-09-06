package org.schors.vertx.telegram;

import io.vertx.core.Handler;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.streams.ReadStream;

import java.io.File;

public class Base64Stream implements ReadStream {

    private AsyncFile file;

    public Base64Stream(File file) {
    }

    @Override
    public ReadStream exceptionHandler(Handler handler) {
        return null;
    }

    @Override
    public ReadStream handler(Handler handler) {
        return null;
    }

    @Override
    public ReadStream pause() {
        return null;
    }

    @Override
    public ReadStream resume() {
        return null;
    }

    @Override
    public ReadStream endHandler(Handler endHandler) {
        return null;
    }
}
