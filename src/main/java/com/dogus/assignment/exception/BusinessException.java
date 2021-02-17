package com.dogus.assignment.exception;


public class BusinessException extends RuntimeException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        return message != null ? message : getResponseCode().getMessage();
    }

    public ResponseCode getResponseCode() {
        return ResponseCode.RECORD_NOT_FOUND;
    }

}
