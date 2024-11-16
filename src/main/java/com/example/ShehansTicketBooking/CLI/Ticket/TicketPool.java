/**
 * This class is used to manage the ticket pool in the system,
 * handling the addition and removal of tickets while ensuring thread safety.
 */

package com.example.ShehansTicketBooking.CLI.Ticket;

public class TicketPool {
    private final int maxTicketCapacity; // Maximum number of tickets that can be in the system
    private int totalTickets; // Current number of tickets in the system

    // Constructor to initialize the ticket pool with a maximum capacity and an initial ticket count
    public TicketPool(int maxTicketCapacity, int totalTickets) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
    }

    // Synchronized method to add tickets to the pool
    public synchronized boolean addTickets(int quantity) {
        // Check if adding the specified quantity exceeds the maximum capacity
        if (totalTickets + quantity <= maxTicketCapacity) {
            totalTickets += quantity;  // Add tickets to the pool
            return true;  // Tickets added successfully
        }
        return false;  // Cannot add tickets as it exceeds the max capacity
    }

    // Synchronized method to remove tickets from the pool
    public synchronized boolean removeTickets(int quantity) {
        // Check if there are enough tickets to remove
        if (totalTickets >= quantity) {
            totalTickets -= quantity;  // Remove tickets from the pool
            return true;  // Tickets removed successfully
        }
        return false;  // Not enough tickets to remove
    }

    // Get the current total number of tickets in the pool
    public int getTotalTickets() {
        return totalTickets;
    }

    // Get the maximum capacity of tickets the pool can hold
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
