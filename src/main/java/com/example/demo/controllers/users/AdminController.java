package com.example.demo.controllers.users;

import com.example.demo.models.DTOS.CreateAccountDTO;
import com.example.demo.models.account.Account;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.models.users.ThirdParty;
import com.example.demo.services.users.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AdminController {

    @Autowired
    AdminService adminService;
    @PostMapping("/saving")
    public Account createSavingAccount(@RequestBody CreateAccountDTO account) {
        return adminService.createSavingAccount(account);
    }

    @PostMapping("/credit-card")
    public Account createCreditCardAccount(@RequestBody CreateAccountDTO account) {
        return adminService.createCreditCardAccount(account);
    }

    @PostMapping("/student-checking-or-checking-student")
    public Account createCheckingOrStudentChecking(@RequestBody CreateAccountDTO account) {
        return adminService.createCheckingOrStudentChecking(account);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAccount(@RequestParam Long id){
        adminService.deleteAccount(id);
    }

    @GetMapping("/access-balance/{id}")
    public BigDecimal accessBalance(@RequestParam Long id){
    return adminService.accessBalance(id);
    }

    @PatchMapping("/modify-balance/{id}")
    public void modifyBalance(@RequestParam Long id, @RequestParam double balance){
        adminService.modifyBalance(id, balance);
    }
    @PostMapping("/create-third-party-user")
    public ThirdParty createThirdPartyUser(@RequestBody ThirdParty thirdParty){
        return adminService.createThirdPartyUser(thirdParty);
    }

    @PostMapping("/create-accountholder")
    public AccountHolders createAccountHolders(@RequestBody AccountHolders accountHolders){
        return adminService.createAccountHolder(accountHolders);
    }
}
