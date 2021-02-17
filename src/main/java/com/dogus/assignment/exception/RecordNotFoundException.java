package com.dogus.assignment.exception;

public class RecordNotFoundException extends BusinessException {

    @Override
    public ResponseCode getResponseCode() {
        return ResponseCode.RECORD_NOT_FOUND;
    }
}
