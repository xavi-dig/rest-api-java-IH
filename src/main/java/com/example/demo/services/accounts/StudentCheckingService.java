package com.example.demo.services.accounts;

import com.example.demo.repositories.account.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCheckingService {

    @Autowired
    StudentCheckingRepository studentCheckingRepository;
}
