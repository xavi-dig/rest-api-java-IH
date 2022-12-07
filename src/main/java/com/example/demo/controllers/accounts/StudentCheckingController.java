package com.example.demo.controllers.accounts;

import com.example.demo.repositories.account.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCheckingController {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;
}
