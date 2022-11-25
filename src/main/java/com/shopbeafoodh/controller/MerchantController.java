package com.shopbeafoodh.controller;

import com.shopbeafoodh.model.entity.Merchant;
import com.shopbeafoodh.service.merchant.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api")
public class MerchantController {
    @Autowired
    private IMerchantService merchantService;

    @GetMapping("/public/merchant")
    public ResponseEntity<Iterable<Merchant>> findAllMerchant(){
        Iterable<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @PutMapping("/merchant/{id}")
    public ResponseEntity<Merchant> updateMerchant (@PathVariable Long id,@RequestBody Merchant merchant){
        return new ResponseEntity<>(merchantService.updateMerchant(id,merchant),HttpStatus.OK);
    }

    @GetMapping("/public/merchant/{id}")
    public ResponseEntity<Merchant> findById(@PathVariable Long id) {
        return new ResponseEntity<>(merchantService.findMerchantById(id), HttpStatus.OK);
    }

    @PostMapping("/merchant")
    public ResponseEntity<Merchant> saveMerchant(@RequestBody Merchant merchant) {
        return new ResponseEntity<>(merchantService.save(merchant), HttpStatus.CREATED);
    }

    @DeleteMapping("/merchant/{id}")
    public ResponseEntity<Merchant> deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/public/merchant/search")
    public ResponseEntity<?> searchMerchant(@RequestParam String name) {
        List<Merchant> merchants = merchantService.findAllContainer(name);
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }
}
