package com.ma.bookies1.dto;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

@Getter
public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String sex;
    private Date dateOfBirth;
    private Set<String> roles;
    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public RegisterUserDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public RegisterUserDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public RegisterUserDto setCity(String city) {
        this.city = city;
        return this;
    }

    public RegisterUserDto setCountry(String country) {
        this.country = country;
        return this;
    }
    public RegisterUserDto setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public RegisterUserDto setSex(String sex){
        this.sex=sex;
        return this;
    }

    public RegisterUserDto setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth=dateOfBirth;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
