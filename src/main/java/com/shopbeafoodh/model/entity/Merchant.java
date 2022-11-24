package com.shopbeafoodh.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String address;
    private String img;
    private String avatar;
    private String status;

    @Column(columnDefinition = "Time")
    private String openTime;

    @Column(columnDefinition = "Time")
    private String closeTime;

    @OneToOne
    private Account account;
}
