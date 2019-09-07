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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your first name")
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your last name")
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Size(min = 1, message = "Please enter number of products")
    @Column(name="number_product")
    private int numberProd;

    @NotNull
    @Size(min = 1, message = "Please enter total price")
    @Column(name="total_price")
    private int totalPrice;

    @Column(name="reference_code")
    private String referenceCode;

    @NotNull
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
