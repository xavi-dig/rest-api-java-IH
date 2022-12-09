package com.example.demo.models.account;

import com.example.demo.models.users.AccountHolders;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class Saving extends Account {

    private BigDecimal minimumBalance;

    private BigDecimal interestRate;

    private LocalDate lastInterestApply = LocalDate.now();

    public Saving(BigDecimal balance, String secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal minimumBalance, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        setMinimumBalance(minimumBalance);
        setInterestRate(interestRate);
    }

    public Saving() {
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        if (minimumBalance == null) {
            this.minimumBalance = new BigDecimal(1000);
            return;
        }
        if (minimumBalance.compareTo(BigDecimal.valueOf(1000)) > 0 || minimumBalance.compareTo(BigDecimal.valueOf(100)) < 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Minimum Balance should be less than 1000 and must be greater than 100");

        this.minimumBalance = minimumBalance;

    }

    public BigDecimal applyPenaltyFee() {
        if (minimumBalance.compareTo(getBalance()) > 0) {
            setBalance(getBalance().subtract(getPenaltyFee()));
        }
        return getBalance();
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null) {
            this.interestRate = new BigDecimal("0.0025");
            return;
        }
        if (interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The interest Rate should be greater than 0.5");
        this.interestRate = interestRate;

    }

    public void applySavingInterest() {
        if (Period.between(lastInterestApply, LocalDate.now()).getYears() > 1) {
            super.setBalance(super.getBalance().add(super.getBalance().multiply(interestRate)
                    .multiply(BigDecimal.valueOf(Period.between(lastInterestApply, LocalDate.now()).getYears()))));
            //Actualizamos el lastInterest a fecha actual sin perder los años
            lastInterestApply=lastInterestApply.plusYears(Period.between(lastInterestApply, LocalDate.now()).getYears());
        }
    }

    public LocalDate getLastInterestApply() {
        return lastInterestApply;
    }
}
