package org.schors.vertx.telegram.bot.util;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

public abstract class ConversionStream implements ReadStream<Buffer> {

    private ReadStream<Buffer> input;

    public ConversionStream(ReadStream<Buffer> input) {
        this.input = input;
    }

    @Override
    public ReadStream<Buffer> exceptionHandler(Handler<Throwable> handler) {
        input.exceptionHandler(event -> handler.handle(event));
        return this;
    }

    @Override
    public ReadStream<Buffer> handler(Handler<Buffer> handler) {
        input.handler(event -> handler.handle(converse(event)));
        return this;
    }

    @Override
    public ReadStream<Buffer> pause() {
        input.pause();
        return this;
    }

    @Override
    public ReadStream<Buffer> resume() {
        input.resume();
        return this;
    }

    @Override
    public ReadStream<Buffer> endHandler(Handler<Void> endHandler) {
        input.endHandler(event -> endHandler.handle(event));
        return this;
    }

    public abstract Buffer converse(Buffer input);
}
