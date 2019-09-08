package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerPayReq implements Serializable {


    private String firstName;
    private String lastName;
    private String address;
    private Integer numberOfProduct;
    private String liveId;


}
