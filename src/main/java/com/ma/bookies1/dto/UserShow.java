package com.ma.bookies1.dto;

import java.util.Date;
import java.util.Set;

public class UserShow {
    private String email;
    // private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String sex;
    private Date dateOfBirth;
    private String UserRole;
    public UserShow setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserShow setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public UserShow setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserShow setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserShow setCity(String city) {
        this.city = city;
        return this;
    }

    public UserShow setCountry(String country) {
        this.country = country;
        return this;
    }
    public UserShow setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public UserShow setSex(String sex){
        this.sex=sex;
        return this;
    }

    public UserShow setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        return this;
    }

    @Override
    public String toString() {
        return "UserShow{" +
                "email='" + email + '\'' +

                ", fullName='" + fullName + '\'' +
                '}';
    }
}
