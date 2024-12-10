package com.example.ShehansTicketBooking.Backend.Models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vendors implements Runnable{
    private final TicketPool ticketPool; // Shared ticket pool
    private final int vendorReleaseRate; // Rate of ticket release per second
    private final String vendorName; //Vendor Name

    private static final Logger logger = LoggerFactory.getLogger(Vendors.class);

    // Constructor initializes the ticket pool, release rate
    public Vendors(TicketPool ticketPool, int vendorReleaseRate, String vendorName) {
        this.ticketPool = ticketPool;
        this.vendorReleaseRate = vendorReleaseRate;
        this.vendorName = vendorName;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            // Attempt to add ticketsManager to the pool
            if (ticketPool.addTickets(vendorReleaseRate)) {
                logger.info("{} added {} Tickets. Total Tickets in system: {}", vendorName, vendorReleaseRate, ticketPool.getCurrentTicket());
            } else {
                logger.info("Max ticket capacity reached. {} is waiting for available space...", vendorName);
            }

            // Check if vendor is out of ticketsManager
            if (ticketPool.getTotalSoldTickets() + ticketPool.getCurrentTicket() == ticketPool.getMaxTicketCapacity()) {
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
