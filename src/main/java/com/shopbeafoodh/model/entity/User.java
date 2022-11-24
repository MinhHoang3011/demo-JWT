package com.shopbeafoodh.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String avatar;
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;
}
