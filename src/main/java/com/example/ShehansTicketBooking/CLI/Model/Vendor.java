package com.example.ShehansTicketBooking.CLI.Model;

public class Vendor extends User{
    private String address;
    private String email;
    private String userName;
    private String password;

    public Vendor(String name, String NIC, int phoneNumber, String address, String email, String userName, String password) {
        super(name, NIC, phoneNumber);
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
