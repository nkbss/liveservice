package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="stock_id")
    private int stockId;

    @NotNull
    @Column(name="user_id")
    private String userId;

    @NotNull
    @Column(name="name")
    @Size(min = 1, max = 100, message = "Please type your name")
    private String name;

    @Column(name="price")
    private double price;

    @NotNull
    @Column(name="in_stock")
    private int inStock;


    @JsonIgnore
    @CreationTimestamp
    @Column(name="created_date")
    private Timestamp createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name="updated_date")
    private Timestamp updatedDate;
}
