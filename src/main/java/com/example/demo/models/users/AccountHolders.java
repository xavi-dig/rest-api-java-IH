package com.example.demo.models.users;

import com.example.demo.models.account.Account;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountHolders extends User{

    private LocalDate dateOfBirth;

    @Embedded
    private Address primaryAddress;

    private String email;

    @OneToMany(mappedBy = "primaryOwner")
    List<Account> primaryAccountList = new ArrayList<>();

    @OneToMany(mappedBy = "secondaryOwner")
    List<Account> secondaryAccountList = new ArrayList<>();

    public AccountHolders(String name, LocalDate dateOfBirth, Address primaryAddress, String email, String password) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.email = email;
    }

    public AccountHolders() {

    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getPrimaryAccountList() {
        return primaryAccountList;
    }

    public void setPrimaryAccountList(List<Account> primaryAccountList) {
        this.primaryAccountList = primaryAccountList;
    }

    public List<Account> getSecondaryAccountList() {
        return secondaryAccountList;
    }

    public void setSecondaryAccountList(List<Account> secondaryAccountList) {
        this.secondaryAccountList = secondaryAccountList;
    }
}
