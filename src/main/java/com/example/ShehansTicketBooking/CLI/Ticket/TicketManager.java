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
    private Thread vendorThread1;
    private Thread vendorThread2;
    private Thread customerThread;
    private Thread customerThread2;

    // Ticket pool holds the current state of tickets, and configuration defines simulation settings
    private final TicketPool ticketPool;
    private final Configuration config;

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
        if (vendorThread1 == null || !vendorThread1.isAlive()) {
            Vendor vendor1 = new Vendor(ticketPool, config.getTicketReleaseRate(), "Vendor-1");
            vendorThread1 = new Thread(vendor1);
            vendorThread1.start();
        }

        if (vendorThread2 == null || !vendorThread2.isAlive()) {
            Vendor vendor2 = new Vendor(ticketPool, config.getTicketReleaseRate(), "Vendor-2");
            vendorThread2 = new Thread(vendor2);
            vendorThread2.start();
        }

        if (customerThread == null || !customerThread.isAlive()) {
            Customer customer = new Customer(ticketPool, config.getCustomerRetrievalRate(), "Customer-1");
            customerThread = new Thread(customer);
            customerThread.start();
        }

        if (customerThread2 == null || !customerThread2.isAlive()) {
            Customer customer2 = new Customer(ticketPool, config.getCustomerRetrievalRate(), "Customer-2");
            customerThread2 = new Thread(customer2);
            customerThread2.start();
        }

        System.out.println("Simulation started.");
    }

    /**
     * Stops the ticketing simulation by interrupting both Vendor and Customer threads
     * and resetting them to null to allow future restarts.
     */
    public void stopSimulation() {
        if (vendorThread1 != null && vendorThread1.isAlive()) {
            vendorThread1.interrupt();
        }

        if (vendorThread2 != null && vendorThread2.isAlive()) {
            vendorThread2.interrupt();
        }

        if (customerThread != null && customerThread.isAlive()) {
            customerThread.interrupt();
        }

        if (customerThread2 != null && customerThread2.isAlive()) {
            customerThread2.interrupt();
        }

        vendorThread1 = null;
        vendorThread2 = null;
        customerThread = null;
        customerThread2 = null;

        System.out.println("Simulation stopped.");
    }
    /**
     * Displays the current ticket status, including maximum ticket capacity, ticket release rate,
     * customer retrieval rate, total tickets currently in the system,
     * Vendor Ticket Counts and Total No of Tickets that customer purchased.
     */
    public void monitorStatus() {
        System.out.println("""
                    
                    ==============================
                    |  Monitoring Ticket Status  |
                    ============================== \n""");
        System.out.println("Max Ticket Capacity in System: " + ticketPool.getMaxTicketCapacity());
        System.out.println("Maximum No of Tickets that can be on the Ticket Pool: " + ticketPool.getTotalTickets());
        System.out.println("Vendor Release Rate (Tickets Per Second): " + config.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate (Tickets Per Second): " + config.getCustomerRetrievalRate());
        System.out.println("Total Tickets Available in the System (Not Sold Yet): " + ticketPool.getCurrentTicket());
        System.out.println("Total Tickets Purchased by Customer: " + ticketPool.getTotalSoldTickets());
        int remainingTickets = ticketPool.getMaxTicketCapacity() - ticketPool.getTotalSoldTickets() - ticketPool.getCurrentTicket();
        System.out.println("Remaining Tickets to Sell: " + remainingTickets);
    }
}
