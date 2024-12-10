package com.example.ShehansTicketBooking.Backend.Models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customers implements Runnable {
    private final TicketPool ticketPool; // The shared ticket pool from which ticketsManager are retrieved
    private final int customerRetrievalRate; // The rate at which the customer retrieves ticketsManager
    private final String customerName; //Customer Name

    private static final Logger logger = LoggerFactory.getLogger(Customers.class);

    // Constructor to initialize the ticket pool and retrieval rate
    public Customers(TicketPool ticketPool, int customerRetrievalRate, String customerName) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        // Continue retrieving ticketsManager while the thread is not interrupted
        while (!Thread.currentThread().isInterrupted()) {
            // Attempt to remove ticketsManager from the pool
            if (ticketPool.removeTickets(customerRetrievalRate)) {
                // Print confirmation of ticket removal and current total
                logger.info("Purchased {} Tickets by {}. No of Tickets available for Purchase: {}", customerRetrievalRate, customerName, ticketPool.getCurrentTicket());
            } else {
                // If no ticketsManager are available, but still not reached max ticket level customer will wait
                logger.info("No Tickets Available for Purchase. {} is waiting for new Tickets...", customerName);
            }

            // If no ticketsManager are available, print message and terminate
            if (ticketPool.getTotalSoldTickets() == ticketPool.getMaxTicketCapacity()) {
                logger.info("No Tickets available. {} left the system.", customerName);
                break;
            }

            // Pause the thread for 1 second before the next retrieval attempt
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Re-interrupt the thread if interrupted during sleep, allowing termination
                Thread.currentThread().interrupt();
            }
        }
    }
}
