package com.example.demo.services.users;

import com.example.demo.models.DTOS.AccessBalanceDTO;
import com.example.demo.models.DTOS.TransferDTO;
import com.example.demo.models.account.Account;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.repositories.account.AccountRepository;
import com.example.demo.repositories.user.AccountHoldersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderService {

    @Autowired
    AccountHoldersRepository accountHoldersRepository;

    @Autowired
    AccountRepository accountRepository;

    public BigDecimal accessBalance(AccessBalanceDTO accessBalanceDTO) {
        AccountHolders accountHolders = accountHoldersRepository.findById(accessBalanceDTO.getUserId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist"));

        Account account = accountRepository.findById(accessBalanceDTO.getAccountId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist"));

        if (account.getPrimaryOwner().getName().equals(accountHolders.getName())
                || account.getSecondaryOwner().getName().equals(accountHolders.getName())) {

                return account.getBalance();

        }
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"You are not the owner of the account");
    }

    public void transfer(TransferDTO transferDTO){
        Account sendingAccount = accountRepository.findById(transferDTO.getSendingId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist"));
        Account receivingAccount = accountRepository.findById(transferDTO.getReceivingId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist"));

        if (transferDTO.getReceivingName().equals(receivingAccount.getPrimaryOwner().getName())
                || transferDTO.getReceivingName().equals(receivingAccount.getSecondaryOwner().getName())){

            if (sendingAccount.getBalance().compareTo(transferDTO.getAmount()) > 0) {
                sendingAccount.setBalance(sendingAccount.getBalance().subtract(transferDTO.getAmount()));
                receivingAccount.setBalance(receivingAccount.getBalance().add(transferDTO.getAmount()));

                accountRepository.save(sendingAccount);
                accountRepository.save(receivingAccount);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The Account doesn't have enough funds");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of receiving doesn't match");
    }


}
