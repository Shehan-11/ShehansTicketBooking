package com.example.ShehansTicketBooking.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions_details")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactionID")
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "tickets_purchased")
    private int ticketsPurchased;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transactions() {
    }

    public Transactions(Long id, String customerName, int ticketsPurchased, LocalDateTime transactionDate) {
        this.id = id;
        this.customerName = customerName;
        this.ticketsPurchased = ticketsPurchased;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTicketsPurchased() {
        return ticketsPurchased;
    }

    public void setTicketsPurchased(int ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
