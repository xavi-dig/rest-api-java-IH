package com.example.demo.services;

import com.example.demo.models.DTOS.CreateCheckingDTO;
import com.example.demo.models.account.Account;
import com.example.demo.models.account.Checking;
import com.example.demo.models.account.StudentChecking;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.repositories.account.AccountRepository;
import com.example.demo.repositories.account.CheckingRepository;
import com.example.demo.repositories.account.StudentCheckingRepository;
import com.example.demo.repositories.user.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    AccountHoldersRepository accountHoldersRepository;


    public Account createChecking(CreateCheckingDTO checkingDTO){
        AccountHolders secondaryOwner = null;

        AccountHolders primaryOwner = accountHoldersRepository.findById(checkingDTO.getPrimaryOwnerId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Primary Owner doesn't exist"));
        if (checkingDTO.getSecondaryOwnerId() != null)
            secondaryOwner = accountHoldersRepository.findById(checkingDTO.getSecondaryOwnerId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Secondary Owner doesn't exist"));


        if(Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() < 24){
            return studentCheckingRepository
                    .save(new StudentChecking(checkingDTO.getBalance(), checkingDTO.getSecretKey(), primaryOwner, secondaryOwner));

        }
        return checkingRepository
                .save(new Checking(checkingDTO.getBalance(),checkingDTO.getSecretKey(), primaryOwner, secondaryOwner));

    }
}
