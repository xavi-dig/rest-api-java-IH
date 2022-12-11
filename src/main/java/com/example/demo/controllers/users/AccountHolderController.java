package com.example.demo.controllers.users;

import com.example.demo.models.DTOS.AccessBalanceDTO;
import com.example.demo.models.DTOS.TransferDTO;
import com.example.demo.services.users.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountHolderController {

    @Autowired
    AccountHolderService accountHolderService;

    @GetMapping("/access-balance")
    public BigDecimal accessBalance(@RequestBody AccessBalanceDTO accessBalanceDTO) {
        return accountHolderService.accessBalance(accessBalanceDTO);
    }

    @PatchMapping("/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) {
        accountHolderService.transfer(transferDTO);
    }
}

