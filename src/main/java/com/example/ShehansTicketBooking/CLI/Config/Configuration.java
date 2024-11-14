package com.example.ShehansTicketBooking.CLI.Config;
import java.time.LocalDateTime;

public class Configuration {
    // Used final because after Saving the configurations to the DB we don't wanna modify the values
    private Integer configID;
    private final Integer maxTicketCapacity;
    private final Integer totalTickets;
    private final double ticketReleaseRate;
    private final double customerRetrievalRate;
    private LocalDateTime lastUpdateDate;

    // This Constructor is to load configurations from the ConfigurationManager Class
    public Configuration(Integer configID, Integer maxTicketCapacity, Integer totalTickets, double ticketReleaseRate, double customerRetrievalRate, LocalDateTime lastUpdateDate) {
        this.configID = configID;
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.lastUpdateDate = lastUpdateDate;
    }

    // Constructor of the Configuration Class - Used in Main Class
    public Configuration(Integer maxTicketCapacity, Integer totalTickets, double ticketReleaseRate, double customerRetrievalRate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public Integer getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public Integer getTotalTickets() {
        return totalTickets;
    }

    public double getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public double getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    // Convert Object to a Readable String
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

//        ConfigurationManager configManager = new ConfigurationManager();

//        // Example user input values
//        Configuration config = new Configuration(550, 55, 10.54, 5.75);
//
//        // Save configuration to the database
//        configManager.saveConfiguration(config);
//
//        // Load the latest configuration
//        Configuration latestConfig = configManager.loadLatestConfig();
//        if (latestConfig != null) {
//            System.out.println("Loaded configuration: " + latestConfig);
//        }
