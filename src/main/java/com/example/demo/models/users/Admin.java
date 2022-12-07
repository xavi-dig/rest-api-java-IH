package com.example.demo.models.users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin(String name) {
        super(name);
    }

    public Admin() {
    }

}


