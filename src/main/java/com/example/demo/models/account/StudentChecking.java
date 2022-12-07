package com.example.demo.models.account;

import com.example.demo.models.users.AccountHolders;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class StudentChecking extends Account{


    public StudentChecking(BigDecimal balance, String secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);

    }

    public StudentChecking() {
    }

}
