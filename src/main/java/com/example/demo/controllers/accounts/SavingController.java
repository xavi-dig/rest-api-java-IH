package com.example.demo.controllers.accounts;

import com.example.demo.repositories.account.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {

    @Autowired
    SavingRepository savingRepository;
}
