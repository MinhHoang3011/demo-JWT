package com.shopbeafoodh.repository;

import com.shopbeafoodh.model.entity.Merchant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IMerchantRepository extends PagingAndSortingRepository<Merchant,Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into merchant(address,avatar,name,phone,status,account_id) values (?1,?2,?3,?4,?5,?6);", nativeQuery = true)
    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);
    @Query(value = "select * from merchant m where m.name like concat('%', ?1, '%')", nativeQuery = true)
    List<Merchant> findAllMerchantAndNameContainer(String name);
    void deleteMerchantById(Long id);

}
