package com.digitalacademy.liveservice.constants;

import lombok.Getter;

@Getter
public enum LiveResponse {

    SUCCESS("2000","success"),
    CREATED("2001","create success"),
    NOT_FOUND("4004", "not found");

    private String code;
    private String message;

    LiveResponse (String code,String message){
        this.code = code;
        this.message =message;
    }

}
