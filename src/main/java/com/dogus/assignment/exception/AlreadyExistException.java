package com.dogus.assignment.exception;

public class AlreadyExistException extends BusinessException {

    @Override
    public ResponseCode getResponseCode() {
        return ResponseCode.RECORD_ALLREADY_EXIST;
    }
}
