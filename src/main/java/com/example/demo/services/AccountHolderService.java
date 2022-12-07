package com.example.demo.services;

import com.example.demo.models.account.Account;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.repositories.user.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountHolderService {

    @Autowired
    AccountHoldersRepository accountHoldersRepository;

}
