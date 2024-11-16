/**
 * This class is used to store and manage the configuration settings for the ticket booking system.
 * Configuration includes maximum ticket capacity, total tickets, ticket release rate, and customer retrieval rate.
 */

package com.example.ShehansTicketBooking.CLI.Config;
import java.time.LocalDateTime;

public class Configuration {
    // Config ID to uniquely identify each configuration instance in the database
    private int configID;
    // Maximum ticket capacity allowed in the system, cannot be modified after creation
    private final int maxTicketCapacity;
    // Total tickets available in the system, cannot be modified after creation
    private final int totalTickets;
    // Rate at which tickets are released, cannot be modified after creation
    private final int ticketReleaseRate;
    // Rate at which customers retrieve tickets, cannot be modified after creation
    private final int customerRetrievalRate;
    // Last updated timestamp for this configuration, for tracking changes
    private LocalDateTime lastUpdateDate;

    /**
     * Constructor to load an existing configuration from the ConfigurationManager class.
     * @param configID Unique identifier for the configuration
     * @param maxTicketCapacity Maximum number of tickets allowed in the system
     * @param totalTickets Total number of tickets available
     * @param ticketReleaseRate Rate at which tickets are released
     * @param customerRetrievalRate Rate at which customers retrieve tickets
     * @param lastUpdateDate Last update date of this configuration
     */
    public Configuration(int configID, int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate, LocalDateTime lastUpdateDate) {
        this.configID = configID;
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * Constructor for creating a new configuration with specific settings.
     * @param maxTicketCapacity Maximum number of tickets allowed in the system
     * @param totalTickets Total number of tickets available
     * @param ticketReleaseRate Rate at which tickets are released
     * @param customerRetrievalRate Rate at which customers retrieve tickets
     */
    public Configuration(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Getter method for maximum ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // Getter method for total tickets
    public int getTotalTickets() {
        return totalTickets;
    }

    // Getter method for ticket release rate
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    // Getter method for customer retrieval rate
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Returns a string representation of the configuration, making it easier to read the details.
     * @return A string describing the configuration's properties
     */
    @Override
    public String toString() {
        return "Configuration {" +
                "configID = " + configID +
                ", maxTicketCapacity = " + maxTicketCapacity +
                ", totalTickets = " + totalTickets +
                ", ticketReleaseRate = " + ticketReleaseRate +
                ", customerRetrievalRate = " + customerRetrievalRate +
                ", lastUpdateDate = " + lastUpdateDate +
                '}';
    }
}
