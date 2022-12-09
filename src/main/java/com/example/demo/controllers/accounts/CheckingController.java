package com.example.demo.controllers.accounts;

import com.example.demo.services.accounts.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckingController {
    @Autowired
    CheckingService checkingService;
}
