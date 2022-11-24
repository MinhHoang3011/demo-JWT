package com.shopbeafoodh.service.role;

import com.shopbeafoodh.model.entity.Role;
import com.shopbeafoodh.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;


    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById() {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void setDefaultRole(Long accountId, Integer roleId) {
        roleRepository.setDefaultRole(accountId,roleId);
    }
}
