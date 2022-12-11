package com.example.demo.services.users;

import com.example.demo.models.DTOS.CreateAccountDTO;
import com.example.demo.models.Role;
import com.example.demo.models.account.*;
import com.example.demo.models.users.AccountHolders;
import com.example.demo.models.users.ThirdParty;
import com.example.demo.repositories.account.*;
import com.example.demo.repositories.user.AccountHoldersRepository;
import com.example.demo.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    SavingRepository savingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    public Account createCheckingOrStudentChecking(CreateAccountDTO createAccountDTO){
        AccountHolders secondaryOwner = null;

        AccountHolders primaryOwner = accountHoldersRepository.findById(createAccountDTO.getPrimaryOwnerId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Primary Owner doesn't exist"));
        if (createAccountDTO.getSecondaryOwnerId() != null)
            secondaryOwner = accountHoldersRepository.findById(createAccountDTO.getSecondaryOwnerId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Secondary Owner doesn't exist"));


        if(Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears() < 24){
            return studentCheckingRepository
                    .save(new StudentChecking(createAccountDTO.getBalance(), createAccountDTO.getSecretKey(), primaryOwner, secondaryOwner));

        }
        return checkingRepository
                .save(new Checking(createAccountDTO.getBalance(),createAccountDTO.getSecretKey(), primaryOwner, secondaryOwner));

    }

    public Account createSavingAccount (CreateAccountDTO createAccountDTO){
        AccountHolders primaryOwner = accountHoldersRepository.findById(createAccountDTO.getPrimaryOwnerId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Primary Owner doesn't exist"));
        AccountHolders secondaryOwner = null;
        if (createAccountDTO.getSecondaryOwnerId() != null)
            secondaryOwner = accountHoldersRepository.findById(createAccountDTO.getSecondaryOwnerId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Secondary Owner doesn't exist"));

        return savingRepository
                .save(new Saving(createAccountDTO.getBalance(), createAccountDTO.getSecretKey(), primaryOwner,
                        secondaryOwner, createAccountDTO.getMinimumBalance(), createAccountDTO.getInterestRate()));
    }

    public Account createCreditCardAccount (CreateAccountDTO createAccountDTO){
        AccountHolders primaryOwner = accountHoldersRepository.findById(createAccountDTO.getPrimaryOwnerId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Primary Owner doesn't exist"));
        AccountHolders secondaryOwner = null;
        if (createAccountDTO.getSecondaryOwnerId() != null)
            secondaryOwner = accountHoldersRepository.findById(createAccountDTO.getSecondaryOwnerId())
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Secondary Owner doesn't exist"));

        return creditCardRepository
                .save(new CreditCard(createAccountDTO.getBalance(), createAccountDTO.getSecretKey(), primaryOwner,
                        secondaryOwner, createAccountDTO.getCreditLimit(), createAccountDTO.getInterestRate()));
    }

    public void deleteAccount(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
        }
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist");
        }


    public BigDecimal accessBalance(Long id){
            Account account = accountRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist"));
            return account.getBalance();
    }

    public void modifyBalance(Long id, double balance){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist"));
        account.setBalance(BigDecimal.valueOf(balance));
        accountRepository.save(account);
    }

    public ThirdParty createThirdPartyUser(ThirdParty thirdParty) {
        return thirdPartyRepository.save(new ThirdParty(thirdParty.getName(), thirdParty.getHashedKey()));
    }

    public AccountHolders createAccountHolder(AccountHolders accountHolders) {
        String encodedPassword = passwordEncoder.encode(accountHolders.getPassword());
        AccountHolders accountHolders1 = new AccountHolders(accountHolders.getName(),
                accountHolders.getDateOfBirth(),accountHolders.getPrimaryAddress(),
                accountHolders.getEmail(), encodedPassword);
        Role role = roleRepository.save(new Role("USER", accountHolders1));
        return accountHoldersRepository.save(accountHolders1);

    }
}
