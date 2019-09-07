package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel {
    @JsonProperty("status")
    private StatusModel status;

    @JsonProperty("data")
    private Object data;

    public ResponseModel(StatusModel statusModel) {
        this.status = statusModel;
    }

    public ResponseModel(StatusModel statusModel, Object data) {
        this.status = statusModel;
        this.data = data;
    }

    public HttpEntity<ResponseModel> build(HttpStatus status) {
        return new ResponseEntity<>(
                new ResponseModel(this.status, this.data),
                status);
    }
}
