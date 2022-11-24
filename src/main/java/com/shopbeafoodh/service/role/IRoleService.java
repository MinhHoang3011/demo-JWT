package com.shopbeafoodh.service.role;

import com.shopbeafoodh.model.entity.Role;
import com.shopbeafoodh.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByName(String name);
    void setDefaultRole(Long accountId, Integer roleId);
}
