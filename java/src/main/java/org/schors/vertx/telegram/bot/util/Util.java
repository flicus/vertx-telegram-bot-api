package org.schors.vertx.telegram.bot.util;

import io.vertx.core.AsyncResult;

public class Util {
    public static final String BASEHOST = "api.telegram.org";
    public static final String BASEURL = "/bot";
    public static final int GETUPDATESTIMEOUT = 50;
    public static final String R_OK = "ok";
    public static final String R_RESULT = "result";
    public static final String R_ERRORDESCRIPTION = "description";
    public static final String R_ERRORCODE = "error_code";

    public static <T> AsyncResult<T> makeAsyncResult(T result, Throwable e) {
        return new AsyncResult<T>() {
            @Override
            public T result() {
                return result;
            }

            @Override
            public Throwable cause() {
                return e;
            }

            @Override
            public boolean succeeded() {
                return e == null;
            }

            @Override
            public boolean failed() {
                return e != null;
            }
        };
    }
}
