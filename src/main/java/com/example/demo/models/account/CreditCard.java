package com.example.demo.models.account;

import com.example.demo.models.users.AccountHolders;
import jakarta.persistence.Entity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{

    private BigDecimal creditLimit;

    private BigDecimal interestRate;

    private LocalDate lastInterestApply = LocalDate.now();


    public CreditCard(BigDecimal balance, String secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard() {
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        if (creditLimit == null){
            this.creditLimit = new BigDecimal(100);
            return;
        }
        if (creditLimit.compareTo(BigDecimal.valueOf(100)) < 0 || (creditLimit.compareTo(BigDecimal.valueOf(100000)) > 0))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Credit Limit should be higher than 100 but not higher than 100000");
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null){
            this.interestRate = new BigDecimal("0.2");
            return;
        }
        if (interestRate.compareTo((BigDecimal.valueOf(0.2))) > 0 || interestRate.compareTo((BigDecimal.valueOf(0.1))) < 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Interest Rate should be less than 0.2 and must be greater than 0.1");
        this.interestRate = interestRate;
    }

    public LocalDate getLastInterestApply() {
        return lastInterestApply;
    }

    public void applyCreditCardInterest() {
        if (Period.between(lastInterestApply, LocalDate.now()).getMonths() > 1) {
            super.setBalance(super.getBalance().add(super.getBalance().multiply(interestRate.divide(BigDecimal.valueOf(12)))
                    .multiply(BigDecimal.valueOf(Period.between(lastInterestApply, LocalDate.now()).getMonths()))));
            //Actualizamos el lastInterest a fecha actual sin perder los meses
            lastInterestApply=lastInterestApply.plusMonths(Period.between(lastInterestApply, LocalDate.now()).getMonths());
        }
    }

}
