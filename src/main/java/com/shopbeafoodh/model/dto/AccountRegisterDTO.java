package com.shopbeafoodh.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterDTO {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String address;
}
