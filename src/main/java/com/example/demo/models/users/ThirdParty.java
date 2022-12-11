package com.example.demo.models.users;

import jakarta.persistence.Entity;

@Entity
public class ThirdParty extends User{

    private String hashedKey;

    public ThirdParty(String name, String hashedKey) {
        super(name, "thirdparty");
        this.hashedKey = hashedKey;
    }

    public ThirdParty() {
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
