package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatusModel {
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public StatusModel(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
