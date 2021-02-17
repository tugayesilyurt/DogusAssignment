package com.dogus.assignment.exception;

import lombok.Getter;

@Getter
public enum ResponseCode {
    RECORD_NOT_FOUND(1000, "Record not found"),
    RECORD_ALLREADY_EXIST(2000, "Record already exist !");;


    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;
}
