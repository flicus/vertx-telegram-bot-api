package org.schors.vertx.telegram.bot.util;

public class NOKResponseException extends Exception {

    private String errorCode;
    private String errorDescription;

    public NOKResponseException() {
    }

    public NOKResponseException(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
