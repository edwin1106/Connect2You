package com.example.connect2you.Dtos;

public class CreateUserDto {
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private double phone;

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public double getPhone(){
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }
}
