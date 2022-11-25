package com.shopbeafoodh.controller;

import com.shopbeafoodh.jwt.JwtUtility;
import com.shopbeafoodh.model.dto.AccountRegisterDTO;
import com.shopbeafoodh.model.dto.AccountToken;
import com.shopbeafoodh.model.entity.Account;
import com.shopbeafoodh.payload.request.LoginRequest;
import com.shopbeafoodh.payload.response.MessageResponse;
import com.shopbeafoodh.service.account.AccountDetails;
import com.shopbeafoodh.service.account.IAccountService;
import com.shopbeafoodh.service.merchant.IMerchantService;
import com.shopbeafoodh.service.role.IRoleService;
import com.shopbeafoodh.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/public")
public class SecurityController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtility.createJwTToken(loginRequest.getUserName());
            AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            Account account = accountService.findByName(loginRequest.getUserName());
            if(account.getMerchant() != null){
                String status= account.getMerchant().getStatus();
                if("pending".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Admin chưa phê duyệt đăng ký merchant"));
                }
                if("block".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Tài khoản của bạn đang bị khóa"));
                }
                if("refuse".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Admin đã từ chối đăng ký merchant"));
                }
            }if(account.getUser() != null){
                String status= account.getUser().getStatus();
                if("block".equals(status)){
                    return ResponseEntity.ok(new MessageResponse("Tài khoản của bạn đang bị khóa"));
                }
            }

            return new ResponseEntity(new AccountToken(account.getId(), account.getUserName(), token, roles, account.getMerchant(),account.getUser()), HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.ok(new MessageResponse("Sai roi"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody AccountRegisterDTO request) {
        if (accountService.existsAccountByUserName(request.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username đã được đăng ký"));
        }
        String status="active";
        boolean isEnabled = true;
        Account account = new Account(request.getUserName(), encoder.encode(request.getPassword()), request.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 2);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";

        userService.saveUserToRegister(request.getAddress(),avatar,request.getName(),request.getPhone(),idAccountAfterCreated,status);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
            }

    @PostMapping("/register/merchant")
    public ResponseEntity<?> addMerchant(@RequestBody AccountRegisterDTO request) {
        if (accountService.existsAccountByUserName(request.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Merchant đã được đăng ký"));
        }

        boolean isEnabled = true;
        Account account = new Account(request.getUserName(), encoder.encode(request.getPassword()), request.getEmail(), isEnabled);
        accountService.save(account);
        Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreated, 3);
        String avatar = "https://scr.vn/wp-content/uploads/2020/07/Avatar-Facebook-tr%E1%BA%AFng.jpg";
        String status = "pending";
        merchantService.saveMerchantToRegister(request.getAddress(),avatar,request.getName(),request.getPhone(),status,idAccountAfterCreated);
        return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công"));
    }
}

