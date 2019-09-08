package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)

@Table(name="livestock")
public class LiveStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "live_id")
    private String liveId;

    @Column(name="user_id")
    private String userId;

    @Column(name="stock_id")
    private int stockId;

    @Column(name="stock_name")
    private String stockName;

    @Column(name="in_stock")
    private int inStock;

    @Column(name="price")
    private double price;

    @Column(name="deep_link")
    private String deepLink;

    @Column(name="close_deal")
    private int closeDeal;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="created_date")
    private Timestamp createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name="updated_date")
    private Timestamp updatedDate;
}
