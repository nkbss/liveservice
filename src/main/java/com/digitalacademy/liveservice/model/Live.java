package com.digitalacademy.liveservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="live")
public class Live {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "live_id")
    private String liveId;

    @Column(name="user_id")
    private String userId;

    @Column(name="live_name")
    private String liveName;

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
