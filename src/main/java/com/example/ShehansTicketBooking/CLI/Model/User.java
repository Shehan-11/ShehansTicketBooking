package com.example.ShehansTicketBooking.CLI.Model;

public class User {
    private long personID;
    private String name;
    private String NIC;
    private int phoneNumber;

    public User (String name, String NIC, int phoneNumber){
        this.name = name;
        this.NIC = NIC;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
