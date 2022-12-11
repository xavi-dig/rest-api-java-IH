package com.example.demo.services.users;

import com.example.demo.models.DTOS.AccessBalanceDTO;
import com.example.demo.models.DTOS.TransferDTO;
import com.example.demo.models.account.Account;
import com.example.demo.models.account.Checking;
import com.example.demo.models.account.CreditCard;
import com.example.demo.models.account.Saving;
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
        System.err.println(accessBalanceDTO.getAccountId());
        if(accountHoldersRepository.findById(1L).isPresent());
        AccountHolders accountHolders = accountHoldersRepository.findById(accessBalanceDTO.getUserId())
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist"));

        Account account = accountRepository.findById(accessBalanceDTO.getAccountId())
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist"));

        if (account.getPrimaryOwner().getName().equals(accountHolders.getName())
                || account.getSecondaryOwner().getName().equals(accountHolders.getName())) {
                if (account instanceof CreditCard){
                    ((CreditCard) account).applyCreditCardInterest();
                } else if (account instanceof Saving) {
                    ((Saving) account).applySavingInterest();

                }
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
                || transferDTO.getReceivingName().equals(receivingAccount.getSecondaryOwner().getName())) {

            if (sendingAccount.getBalance().compareTo(transferDTO.getAmount()) > 0) {
                sendingAccount.setBalance(sendingAccount.getBalance().subtract(transferDTO.getAmount()));
                receivingAccount.setBalance(receivingAccount.getBalance().add(transferDTO.getAmount()));

                if (sendingAccount instanceof Checking) {
                    ((Checking) sendingAccount).applyPenaltyFee();
                } else if (sendingAccount instanceof Saving) {
                    ((Saving) sendingAccount).applyPenaltyFee();

                }
                if (receivingAccount instanceof Checking) {
                    ((Checking) receivingAccount).applyPenaltyFee();
                } else if (receivingAccount instanceof Saving) {
                    ((Saving) receivingAccount).applyPenaltyFee();

                }

                accountRepository.save(sendingAccount);
                accountRepository.save(receivingAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Account doesn't have enough funds");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name of receiving doesn't match");
        }
    }

}
