package com.example.ShehansTicketBooking.Backend.Models;

public class TicketsManager {
    // Threads for handling Vendor and Customer ticket actions
    private Thread vendorThread1;
    private Thread vendorThread2;
    private Thread customerThread;
    private Thread customerThread2;

    // Ticket pool holds the current state of ticketsManager, and configuration defines simulation settings
    private final Tickets tickets;
    private final Configuration config;

    public TicketsManager(Tickets tickets, Configuration config) {
        this.tickets = tickets;
        this.config = config;
    }

    /**
     * Starts the ticketing simulation by creating and starting Vendor and Customer threads,
     * ensuring each thread runs only if not already active.
     */
    public void startSimulation() {
        if (vendorThread1 == null || !vendorThread1.isAlive()) {
            Vendors vendor1 = new Vendors(tickets, config.getTicketReleaseRate(), "Vendor-01");
            vendorThread1 = new Thread(vendor1);
            vendorThread1.start();
        }

        if (vendorThread2 == null || !vendorThread2.isAlive()) {
            Vendors vendor2 = new Vendors(tickets, config.getTicketReleaseRate(), "Vendor-02");
            vendorThread2 = new Thread(vendor2);
            vendorThread2.start();
        }

        if (customerThread == null || !customerThread.isAlive()) {
            Customers customer = new Customers(tickets, config.getCustomerRetrievalRate(), "Customer-01");
            customerThread = new Thread(customer);
            customerThread.start();
        }

        if (customerThread2 == null || !customerThread2.isAlive()) {
            Customers customer2 = new Customers(tickets, config.getCustomerRetrievalRate(), "Customer-02");
            customerThread2 = new Thread(customer2);
            customerThread2.start();
        }
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
    }
    /**
     * Displays the current ticket status, including maximum ticket capacity, ticket release rate,
     * customer retrieval rate, total ticketsManager currently in the system,
     * Vendor Ticket Counts and Total No of TicketsManager that customer purchased.
     */
    public String monitorStatus() {
        StringBuilder statusReport = new StringBuilder();
        //Max Ticket Capacity in System
        statusReport.append("\nMaxTicketCapacity: ").append(tickets.getMaxTicketCapacity());
        //Maximum No of TicketsManager that can be on the Ticket Pool
        statusReport.append("\nTotalTickets: ").append(tickets.getTotalTickets());
        //Vendor Release Rate (TicketsManager Per Second)
        statusReport.append("\nTicketReleaseRate: ").append(config.getTicketReleaseRate());
        //Customer Retrieval Rate (TicketsManager Per Second)
        statusReport.append("\nCustomerRetrievalRate: ").append(config.getCustomerRetrievalRate());
        //Total TicketsManager Available in the System (Not Sold Yet)
        statusReport.append("\nCurrentTicket: ").append(tickets.getCurrentTicket());
        //Total TicketsManager Purchased by Customer
        statusReport.append("\nTotalSoldTickets: ").append(tickets.getTotalSoldTickets());
        int remainingTickets = tickets.getMaxTicketCapacity() - tickets.getTotalSoldTickets() - tickets.getCurrentTicket();
        //Remaining TicketsManager to Sell
        statusReport.append("\nremainingTickets: ").append(remainingTickets);
        return statusReport.toString();
    }
}
