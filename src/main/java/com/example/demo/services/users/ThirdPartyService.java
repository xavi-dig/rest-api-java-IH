package com.example.demo.services.users;

import com.example.demo.models.DTOS.TransferDTO;
import com.example.demo.models.DTOS.TransferThirdPartyDTO;
import com.example.demo.models.account.Account;
import com.example.demo.models.users.ThirdParty;
import com.example.demo.repositories.account.AccountRepository;
import com.example.demo.repositories.user.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ThirdPartyService {

    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Autowired
    AccountRepository accountRepository;

    public void transferThirdParty(TransferThirdPartyDTO transferThirdPartyDTO, String hashedKey){
        Account account = accountRepository.findById(transferThirdPartyDTO.getAccountId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This account doesn't exist"));
        ThirdParty thirdParty = thirdPartyRepository.findById(transferThirdPartyDTO.getThirdPartyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This user doesn't exist"));

        if (hashedKey.equals(thirdParty.getHashedKey()) && transferThirdPartyDTO.getAccountSecretKey().equals(account.getSecretKey())){
            account.setBalance(account.getBalance().add(transferThirdPartyDTO.getAmount()));
            //Si el amount es negativo me están pidiendo €€ y si es positivo me lo están enviando
            accountRepository.save(account);
        } throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "HashedKey or Name aren't correct");
    }

}
