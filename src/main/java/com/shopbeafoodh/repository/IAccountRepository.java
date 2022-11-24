package com.shopbeafoodh.repository;

import com.shopbeafoodh.model.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {
    @Query("select a.user_name from account as a where a.user_name = ?1")
    Account findByUserName(String name);

    boolean existsAccountByUserName(String username);

    @Query(value = "select id from  account where user_name = ?1", nativeQuery = true)
    Long findIdUserByUserName(String username);

    @Query(nativeQuery = true, value = "select * from account join merchant m on account.id = m.account_id and m.id =?1")
    Optional<Account> findAccByMerchantId(Long id);

    @Query(nativeQuery = true, value = "select * from account join app_user a on account.id = a.account_id and a.id =?1")
    Optional<Account> findAccByUserId(Long id);

    Optional<Account> findAccountById(Long id);
}
