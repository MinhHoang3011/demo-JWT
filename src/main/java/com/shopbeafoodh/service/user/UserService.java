package com.shopbeafoodh.service.user;

import com.shopbeafoodh.exception.UserNotFoundException;
import com.shopbeafoodh.model.entity.User;
import com.shopbeafoodh.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById() {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
    userRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(404, "AppUser by id "+id+ " was not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        User updateUser = this.findUserById(id);
        user.setId(updateUser.getId());
        user.setAccount(updateUser.getAccount());
        return userRepository.save(user);
    }

    @Override
    public void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status) {
        userRepository.saveUserToRegister(address,avatar,name,phone,account_id,status);
    }
}
