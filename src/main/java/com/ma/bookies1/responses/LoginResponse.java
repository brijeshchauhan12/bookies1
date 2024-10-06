package com.ma.bookies1.responses;

import com.ma.bookies1.entity.Role;

public class LoginResponse {
    private String token;

    private long expiresIn;

    private String roles;

    public String getRole() {
        return roles;
    }

    public LoginResponse setRole(String roles) {
        this.roles = roles;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}