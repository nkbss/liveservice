package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;

    @Column(name="customer_id")
    private String customerId;

    @Column(name="stock_id")
    private int stockId;

    @Column(name="stock_name")
    private String stockName;

    @Column(name="live_id")
    private String liveId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name="quantity_product")
    private int qtyProd;

    @NotNull
    @Column(name="total_price")
    private double totalPrice;

    @Column(name="reference_code")
    private String referenceCode;

    @Size(min = 1, max = 250 , message = "Please enter your address")
    private String address;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="created_date")
    private Timestamp createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name="updated_date")
    private Timestamp updatedDate;
}
