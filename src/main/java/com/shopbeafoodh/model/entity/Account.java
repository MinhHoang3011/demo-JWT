package com.shopbeafoodh.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private String userName;
    private String password;
    private boolean isEnabled;
    private String email;
    private String otp;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToOne(mappedBy = "account")
    private Merchant merchant;

    @OneToMany(mappedBy = "account")
    private Set<AccountRoleMap> accountRoleMapSet;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Account(String userName, String password, boolean isEnabled, Set<AccountRoleMap> accountRoleMapSet) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.accountRoleMapSet = accountRoleMapSet;
    }

    public Account(String userName, String password, boolean isEnabled, String email) {
        this.userName = userName;
        this.password = password;
        this.isEnabled = isEnabled;
        this.email = email;
    }

    public Account(String userName, String encode, String email, boolean isEnabled) {
    }
}
