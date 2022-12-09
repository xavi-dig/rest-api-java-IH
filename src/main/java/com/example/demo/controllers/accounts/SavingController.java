package com.example.demo.controllers.accounts;

import com.example.demo.services.accounts.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SavingController {

    @Autowired
    SavingService savingService;
}
