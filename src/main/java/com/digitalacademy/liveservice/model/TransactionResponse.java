package com.digitalacademy.liveservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse implements Serializable {

    @NonNull
    private List<Transaction> transactionList;
    @NonNull
    private Integer totalPrice;

}
