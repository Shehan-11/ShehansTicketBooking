/**
 * This class is used to represent a vendor in the ticketing system,
 * responsible for adding tickets to the TicketPool at a specified rate.
 */

package com.example.ShehansTicketBooking.CLI.Model;

import com.example.ShehansTicketBooking.CLI.Ticket.TicketPool;

public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Shared ticket pool
    private final int vendorReleaseRate; // Rate of ticket release per second
    private int vendorInventory;         // Total tickets this vendor has available to sell

    // Constructor initializes the ticket pool, release rate, and vendor's own ticket inventory
    public Vendor(TicketPool ticketPool, int vendorReleaseRate, int vendorInventory) {
        this.ticketPool = ticketPool;
        this.vendorReleaseRate = vendorReleaseRate;
        this.vendorInventory = vendorInventory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && vendorInventory > 0) {
            // Determine how many tickets to add, based on vendor's remaining inventory and Release Rate. Return the Min of the VRR or VI
            int ticketsToAdd = Math.min(vendorReleaseRate, vendorInventory);

            // Attempt to add tickets to the pool
            if (ticketPool.addTickets(ticketsToAdd)) {
                vendorInventory -= ticketsToAdd; // Reduce vendor's own inventory
                System.out.println("Vendor added " + ticketsToAdd + " tickets. Total tickets in system: " + ticketPool.getTotalTickets());
            } else {
                System.out.println("Max ticket capacity reached. Vendor is waiting for available space...");
            }

            // Check if vendor is out of tickets
            if (vendorInventory <= 0) {
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

    // Method to get the current inventory of this vendor
    public int getVendorInventory() {
        return vendorInventory;
    }
}
