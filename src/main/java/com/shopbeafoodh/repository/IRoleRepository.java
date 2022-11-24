package com.shopbeafoodh.repository;

import com.shopbeafoodh.model.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IRoleRepository extends PagingAndSortingRepository<Role,Long> {
    Optional<Role> findByName(String name);
    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values (?1,?2)", nativeQuery = true)
    void setDefaultRole(Long accountId, Integer roleId);
}
