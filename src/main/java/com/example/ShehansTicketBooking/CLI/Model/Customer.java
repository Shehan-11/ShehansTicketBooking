/**
 * This class is used to represent a customer in the ticketing system,
 * responsible for retrieving tickets from the TicketPool at a specified rate.
 */

package com.example.ShehansTicketBooking.CLI.Model;

import com.example.ShehansTicketBooking.CLI.Ticket.TicketPool;

public class Customer implements Runnable {
    private final TicketPool ticketPool; // The shared ticket pool from which tickets are retrieved
    private final int customerRetrievalRate; // The rate at which the customer retrieves tickets
    private int ticketsPurchased = 0; // Tracks the total tickets purchased by this customer

    // Constructor to initialize the ticket pool and retrieval rate
    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        // Continue retrieving tickets while the thread is not interrupted
        while (!Thread.currentThread().isInterrupted()) {
            // Attempt to remove tickets from the pool
            if (ticketPool.removeTickets(customerRetrievalRate)) {
                ticketsPurchased += customerRetrievalRate; // Increment the tickets purchased counter
                // Print confirmation of ticket removal and current total
                System.out.println("Purchased " + customerRetrievalRate + " tickets by a Customer. Total Tickets in The System: " + ticketPool.getTotalTickets());
            } else {
                // If no tickets are available, print message and terminate
                System.out.println("No tickets available. Customer left the system.");
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

    // Method to retrieve the number of tickets purchased by this customer
    public int getTicketsPurchased() {
        return ticketsPurchased;
    }
}
