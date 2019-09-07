package com.digitalacademy.liveservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LiveException extends Exception {
    private LiveException liveException;
    private HttpStatus httpStatus;

    public LiveException(LiveException liveException, HttpStatus httpStatus){
        this.liveException = liveException;
        this.httpStatus = httpStatus;
    }

}
