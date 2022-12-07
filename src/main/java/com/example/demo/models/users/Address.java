package com.example.demo.models.users;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String address;

    private int postalCode;

    private String city;

    private String country;
}
