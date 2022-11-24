package com.shopbeafoodh.repository;

import com.shopbeafoodh.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User,Long> {
    Optional<User> findUserById(Long id);

    @Modifying
    @Transactional
    @Query(value = "insert into app_user(address,avatar,name,phone,account_id,status) values (?1,?2,?3,?4,?5,?6)", nativeQuery = true)
    void saveUserToRegister(String address, String avatar, String name, String phone, Long account_id, String status);
}
