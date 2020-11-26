package com.example.connect2you.Dtos;

public class UserCredentialsDto {
    private double phone;
    private String password;

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
