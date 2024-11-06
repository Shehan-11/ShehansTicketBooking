package com.example.ShehansTicketBooking.CLI.Model;

public class Customer extends User{
    private String userName;
    private String password;

    public Customer(String name, String NIC, int phoneNumber, String userName, String password) {
        super(name, NIC, phoneNumber);
        this.userName = userName;
        this.password = password;
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
