/**
 * This class is used to manage the ticket simulation, including starting, stopping, and monitoring the ticket
 * buying and selling processes through Vendor and Customer threads.
 */

package com.example.ShehansTicketBooking.CLI.Ticket;

import com.example.ShehansTicketBooking.CLI.Config.Configuration;
import com.example.ShehansTicketBooking.CLI.Model.Customer;
import com.example.ShehansTicketBooking.CLI.Model.Vendor;

public class TicketManager {
    // Threads for handling Vendor and Customer ticket actions
    private Thread vendorThread;
    private Thread vendorThread2;
    private Thread customerThread;

    // Ticket pool holds the current state of tickets, and configuration defines simulation settings
    private final TicketPool ticketPool;
    private final Configuration config;

    private Vendor vendor1;
    private Vendor vendor2;
    private Customer customer;

    /**
     * Constructor to initialize TicketManager with a specific ticket pool and configuration.
     * @param ticketPool The pool of tickets shared between Vendor and Customer
     * @param config Configuration settings for ticket release and retrieval rates
     */
    public TicketManager(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    /**
     * Starts the ticketing simulation by creating and starting Vendor and Customer threads,
     * ensuring each thread runs only if not already active.
     */
    public void startSimulation() {
        // Initialize and start the Vendor thread if it's not already running
        if (vendorThread == null || !vendorThread.isAlive()) {
            vendor1 = new Vendor(ticketPool, config.getTicketReleaseRate(), 100); // Vendor01 with 100 tickets
            vendorThread = new Thread(vendor1);
            vendorThread.start();
        }

        // Initialize and start the Vendor2 thread if it's not already running to simulate 2 vendors adding Tickets
        if (vendorThread2 == null || !vendorThread2.isAlive()) {
            vendor2 = new Vendor(ticketPool, config.getTicketReleaseRate(), 50); // Vendor02 with 50 tickets
            vendorThread2 = new Thread(vendor2);
            vendorThread2.start();  // Start the Vendor thread
        }

        // Initialize and start the Customer thread if it's not already running
        if (customerThread == null || !customerThread.isAlive()) {
            customer = new Customer(ticketPool, config.getCustomerRetrievalRate());
            customerThread = new Thread(customer);
            customerThread.start();  // Start the Customer thread
        }

        System.out.println("Simulation started.");  // Confirm simulation has started
    }

    /**
     * Stops the ticketing simulation by interrupting both Vendor and Customer threads
     * and resetting them to null to allow future restarts.
     */
    public void stopSimulation() {
        // Interrupt the Vendor thread if it is currently active
        if (vendorThread != null && vendorThread.isAlive()) {
            vendorThread.interrupt();
        }

        // Interrupt the Vendor2 thread if it is currently active
        if (vendorThread2 != null && vendorThread2.isAlive()) {
            vendorThread2.interrupt();
        }

        // Interrupt the Customer thread if it is currently active
        if (customerThread != null && customerThread.isAlive()) {
            customerThread.interrupt();
        }

        // Set the threads to null after stopping, allowing restart if needed
        vendorThread = null;
        vendorThread2 = null;
        customerThread = null;
        System.out.println("Simulation stopped.");  // Confirm simulation has stopped
    }

    /**
     * Displays the current ticket status, including maximum ticket capacity, ticket release rate,
     * customer retrieval rate, and total tickets currently in the system.
     */
    public void monitorStatus() {
        System.out.println("""
                    
                    ==============================
                    |  Monitoring Ticket Status  |
                    ============================== \n""");
        System.out.println("Max Ticket Capacity in the System: " + ticketPool.getMaxTicketCapacity());  // Display max ticket capacity
        System.out.println("Vendor Release Rate (Tickets Per Second): " + config.getTicketReleaseRate());  // Display vendor release rate
        System.out.println("Customer Retrieval Rate (Tickets Per Second): " + config.getCustomerRetrievalRate());  // Display customer retrieval rate
        System.out.println("Total Tickets in The System (At the Moment): " + ticketPool.getTotalTickets());  // Display total ticket count
        System.out.println("Vendor 1 Tickets Left: " + (vendor1 != null ? vendor1.getVendorInventory() : "Not Available"));
        System.out.println("Vendor 2 Tickets Left: " + (vendor2 != null ? vendor2.getVendorInventory() : "Not Available"));
        System.out.println("Total Tickets Purchased by Customer: " + (customer != null ? customer.getTicketsPurchased() : "Not Available"));
    }
}
