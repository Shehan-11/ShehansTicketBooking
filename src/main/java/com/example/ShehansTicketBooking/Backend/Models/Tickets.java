package com.example.ShehansTicketBooking.Backend.Models;

public class Tickets {
    private final int maxTicketCapacity; // Maximum number of tickets that the system can hold overall
    private final int totalTickets; // Total number of tickets available in the system at a given time
    private int currentTicket = 0; // Number of TicketsManager that is on the System at the moment. currentTicket <= totalTickets
    private int totalSoldTickets = 0; // Number of TicketsManager that was Sold though the System. totalSoldTickets <= maxTicketCapacity

    public Tickets(int maxTicketCapacity, int totalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
    }

    // Synchronized method to add tickets
    public synchronized boolean addTickets(int quantity) {
        // If Current Ticket + Adding Ticket Quantity is less than or equal to total tickets and Sold TicketsManager + Adding Quantity + Current TicketsManager in the Pool
        // is less than or Equal to max ticket capacity this will add tickets to the pool
        if (currentTicket + quantity <= totalTickets && totalSoldTickets + quantity + currentTicket <= maxTicketCapacity) {
            currentTicket += quantity;  // Add tickets to the pool
            return true;  // TicketsManager added successfully
        }
        return false;  // Cannot add tickets as it exceeds the total tickets limit
    }

    // Synchronized method to remove tickets
    public synchronized boolean removeTickets(int quantity) {
        // Check if there are enough tickets to remove
        if (currentTicket >= quantity) {
            currentTicket -= quantity;  // Remove tickets from the pool
            totalSoldTickets += quantity;  // Increment sold tickets
            return true;  // TicketsManager removed successfully
        }
        return false;  // Not enough tickets to remove
    }

    // Get the Max number of tickets in the pool
    public int getTotalTickets() {
        return totalTickets;
    }

    // Get the maximum capacity of tickets the pool can hold
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // Get the current number of tickets added by vendors
    public int getCurrentTicket() {
        return currentTicket;
    }

    // Get the total number of tickets sold to customers
    public int getTotalSoldTickets() {
        return totalSoldTickets;
    }
}