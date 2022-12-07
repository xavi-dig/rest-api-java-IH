package com.example.demo.models.account;

import com.example.demo.models.users.AccountHolders;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Saving extends Account{

    private BigDecimal minimumBalance;

    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private BigDecimal interestRate;

    public Saving(BigDecimal balance, String secretKey, AccountHolders primaryOwner, AccountHolders secondaryOwner, BigDecimal minimumBalance, LocalDate creationDate, Status status, BigDecimal interestRate) {
        super(balance, secretKey, primaryOwner, secondaryOwner);
        setMinimumBalance(minimumBalance);
        this.creationDate = creationDate;
        this.status = status;
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
        if(minimumBalance.compareTo(BigDecimal.valueOf(1000)) > 0 || minimumBalance.compareTo(BigDecimal.valueOf(100)) < 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The Minimum Balance should be less than 1000 and must be greater than 100");

        this.minimumBalance = minimumBalance;

    }

    public BigDecimal applyPenaltyFee(){
        if (minimumBalance.compareTo(getBalance()) > 0)
        {
            setBalance(getBalance().subtract(getPenaltyFee()));
        }
        return getBalance();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (interestRate == null){
            this.interestRate = new BigDecimal("0.0025");
            return;
        }
        if (interestRate.compareTo(BigDecimal.valueOf(0.5)) > 0)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The interest Rate should be greater than 0.5");
        this.interestRate = interestRate;
    }
}
