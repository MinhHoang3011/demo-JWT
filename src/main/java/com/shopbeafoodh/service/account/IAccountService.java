package com.shopbeafoodh.service.account;

import com.shopbeafoodh.model.entity.Account;
import com.shopbeafoodh.service.IGeneralService;

public interface IAccountService extends IGeneralService<Account> {
    Account findByName(String name);
    Long findIdUserByUserName(String username);

    boolean existsAccountByUserName(String username);
    Account findAccByMerchantId(Long id);

    Account findAccByUserId(Long id);

    Account findAccountById(Long id);

    Account updateAccountUserInfo(Long id, Account account);
    Account updateAccountMerchantInfo(Long id, Account account);
    Account updateAccount(Long id, Account account);
}
