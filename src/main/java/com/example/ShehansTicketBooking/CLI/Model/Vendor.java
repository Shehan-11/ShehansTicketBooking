/**
 * This class is used to represent a vendor in the ticketing system,
 * responsible for adding tickets to the TicketPool at a specified rate.
 */

package com.example.ShehansTicketBooking.CLI.Model;

import com.example.ShehansTicketBooking.CLI.Ticket.TicketPool;

public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Shared ticket pool to which tickets are added
    private final int vendorReleaseRate; // The rate at which the vendor releases tickets

    // Constructor to initialize the ticket pool and release rate
    public Vendor(TicketPool ticketPool, int vendorReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorReleaseRate = vendorReleaseRate;
    }

    @Override
    public void run() {
        // Continue adding tickets while the thread is not interrupted
        while (!Thread.currentThread().isInterrupted()) {
            // Attempt to add tickets to the pool
            if (!ticketPool.addTickets(vendorReleaseRate)) {
                // If max capacity is reached, print message and terminate
                System.out.println("Max ticket capacity reached. Vendor Left the System.");
                break;
            }
            // Print confirmation of tickets added and current total
            System.out.println("Added " + vendorReleaseRate + " tickets by a Vendor. Total Tickets in The System: " + ticketPool.getTotalTickets());

            // Pause the thread for 1 second before the next release attempt
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Re-interrupt the thread if interrupted during sleep, allowing termination
                Thread.currentThread().interrupt();
            }
        }
    }
}
