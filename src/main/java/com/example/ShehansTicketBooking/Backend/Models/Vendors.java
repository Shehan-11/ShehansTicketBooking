package com.example.ShehansTicketBooking.Backend.Models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendors implements Runnable{
    private final Tickets tickets; // Shared ticket pool
    private final int vendorReleaseRate; // Rate of ticket release per second
    private final String vendorName; //Vendor Name

    private static final Logger logger = LoggerFactory.getLogger(Vendors.class);

    // Constructor initializes the ticket pool, release rate
    public Vendors(Tickets tickets, int vendorReleaseRate, String vendorName) {
        this.tickets = tickets;
        this.vendorReleaseRate = vendorReleaseRate;
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            // Attempt to add ticketsManager to the pool
            if (tickets.addTickets(vendorReleaseRate)) {
                logger.info("{} added {} Tickets. Total Tickets in system: {}", vendorName, vendorReleaseRate, tickets.getCurrentTicket());
            } else {
                logger.info("Max ticket capacity reached. {} is waiting for available space...", vendorName);
            }

            // Check if vendor is out of ticketsManager
            if (tickets.getTotalSoldTickets() + tickets.getCurrentTicket() == tickets.getMaxTicketCapacity()) {
                logger.info("{} has sold all Tickets and left the system.", vendorName);
                break;
            }

            // Pause before the next release attempt
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Re-interrupt the thread to exit gracefully
            }
        }
    }
}
