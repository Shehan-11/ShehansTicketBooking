/**
 * This class is used to represent a vendor in the ticketing system,
 * responsible for adding tickets to the TicketPool at a specified rate.
 */

package com.example.ShehansTicketBooking.CLI.Model;

import com.example.ShehansTicketBooking.CLI.Ticket.TicketPool;

public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Shared ticket pool
    private final int vendorReleaseRate; // Rate of ticket release per second

    // Constructor initializes the ticket pool, release rate
    public Vendor(TicketPool ticketPool, int vendorReleaseRate) {
        this.ticketPool = ticketPool;
        this.vendorReleaseRate = vendorReleaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            // Attempt to add tickets to the pool
            if (ticketPool.addTickets(vendorReleaseRate)) {
                System.out.println("Vendor added " + vendorReleaseRate + " tickets. Total tickets in system: " + ticketPool.getCurrentTicket());
            } else {
                System.out.println("Max ticket capacity reached. Vendor is waiting for available space...");
            }

            // Check if vendor is out of tickets
            if (ticketPool.getTotalSoldTickets() + ticketPool.getCurrentTicket() == ticketPool.getMaxTicketCapacity()) {
                System.out.println("Vendor has sold all tickets and left the system.");
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
