package com.shopbeafoodh.service.user;

import com.shopbeafoodh.model.entity.User;
import com.shopbeafoodh.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    User findUserById(Long id);
    User updateUser(Long id, User user);


    void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status);
}
