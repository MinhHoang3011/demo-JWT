package com.shopbeafoodh.service.merchant;

import com.shopbeafoodh.exception.MerchantNotFoundException;
import com.shopbeafoodh.model.entity.Merchant;
import com.shopbeafoodh.repository.IMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {

    @Autowired
    private IMerchantRepository merchantRepository;


    @Override
    public Iterable<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Optional<Merchant> findById() {
        return Optional.empty();
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public void remove(Long id) {
        merchantRepository.deleteById(id);
    }


    @Override
    public void saveMerchantToRegister(String address, String avatar, String name, String phone, String status, Long accountID) {
        merchantRepository.saveMerchantToRegister(address, avatar, name, phone, status, accountID);
    }

    @Override
    public List<Merchant> findAllContainer(String name) {
        return merchantRepository.findAllMerchantAndNameContainer(name);
    }

    @Override
    public Merchant findMerchantById(Long id) {
        return merchantRepository.findById(id).orElseThrow(() -> new MerchantNotFoundException(404,"Merchant by id " + id + " was not found"));
    }

    @Override
    public Merchant updateMerchant(Long id, Merchant merchant) {
        Merchant merchant1 = this.findMerchantById(id);
        merchant.setId(merchant1.getId());
        merchant.setAccount(merchant1.getAccount());
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteMerchantById(Long id) {
        merchantRepository.deleteMerchantById(id);
    }
}
