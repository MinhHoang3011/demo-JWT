package com.shopbeafoodh.service.merchant;

import com.shopbeafoodh.model.entity.Merchant;
import com.shopbeafoodh.service.IGeneralService;

import java.util.List;

public interface IMerchantService extends IGeneralService<Merchant> {
    void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID);

    List<Merchant> findAllContainer(String name);

    Merchant findMerchantById(Long id);

    Merchant updateMerchant(Long id, Merchant merchant);

    void deleteMerchantById(Long id);
}
