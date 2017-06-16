package org.schors.vertx.telegram.bot.util;


import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.ReadStream;

import java.util.Base64;

public class Base64Stream extends ConversionStream {

    private Base64.Encoder encoder = Base64.getMimeEncoder(80, System.lineSeparator().getBytes());

    public Base64Stream(ReadStream<Buffer> input) {
        super(input);
    }

    @Override
    public Buffer converse(Buffer input) {
        return Buffer.buffer(encoder.encode(input.getBytes()));
    }
}
