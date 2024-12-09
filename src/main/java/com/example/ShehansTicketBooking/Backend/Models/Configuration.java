package com.example.ShehansTicketBooking.Backend.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "config_details")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ConfigID")
    private Long configID;
    @Column(name = "Max_Ticket_Capacity")
    private int maxTicketCapacity;
    @Column(name = "Total_Tickets")
    private int totalTickets;
    @Column(name = "Ticket_Release_Rate")
    private int ticketReleaseRate;
    @Column(name = "Customer_Retrieval_Rate")
    private int customerRetrievalRate;
    @Column(name = "Last_Update_Date")
    private LocalDateTime lastUpdateDate;

    public Configuration(){}

    public Configuration(Long configID, int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate, LocalDateTime lastUpdateDate) {
        this.configID = configID;
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
