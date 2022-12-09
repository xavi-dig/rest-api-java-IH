package com.example.demo.models.account;

import com.example.demo.models.users.AccountHolders;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Checking extends Account{

    private final BigDecimal minimumBalance = new BigDecimal(250);

    private final BigDecimal monthlyMaintenanceFee = new BigDecimal(12);


    public Checking(BigDecimal balance, String secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner) {
        super(balance, secretKey, primaryOwner, secondaryOwner);

    }

    public Checking() {
    }

    public BigDecimal applyPenaltyFee(){
        if (minimumBalance.compareTo(getBalance()) > 0)
        {
            setBalance(getBalance().subtract(getPenaltyFee()));
        }
        return getBalance();
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }
}
