/**
 * This class is used to handle user input and validation for ticket booking system configurations.
 * It provides methods for validating maximum ticket capacity, total tickets, ticket release rate,
 * and customer retrieval rate.
 */

package com.example.ShehansTicketBooking.CLI.Config;

import java.util.Scanner;

public class ConfigInputsAndValidation {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * This Method is used to take input for tha Max Ticket and Validate it
     *
     * @return maxTicketCapacity - the validated maximum ticket capacity
     */
    public static int validateMaxTicketCapacity() {
        int maxTicketCapacity;
        while (true) {
            System.out.println();
            System.out.print("Enter Total No Tickets for the Event (Max Ticket Capacity): ");
            if (scanner.hasNextInt()) {
                maxTicketCapacity = scanner.nextInt();
                if (maxTicketCapacity > 0) {
                    return maxTicketCapacity;
                } else {
                    System.out.println("Error: Max Ticket Capacity must be greater than 0");
                }
            } else {
                System.out.println("Error: Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
    }

    /**
     * This Method is used to take input for tha Total Tickets and Validate it. This method takes a maxTicketCapacity as a
     * Parameter to compare Total Tickets with Max Tickets
     *
     * @param maxTicketCapacity - the maximum allowed ticket capacity for the system
     * @return totalTickets - the validated total tickets available
     */
    public static int validateTotalTickets(int maxTicketCapacity) {
        int totalTickets;
        while (true) {
            System.out.print("Enter Total No of Tickets Available in the System at a Given Time: ");
            if (scanner.hasNextInt()) {
                totalTickets = scanner.nextInt();
                if (totalTickets > 0 && totalTickets <= maxTicketCapacity) {
                    return totalTickets;
                } else {
                    System.out.println("Error: Total Tickets must be greater than zero and less than or equal to the Maximum Ticket Capacity");
                }
            } else {
                System.out.println("Error: Please enter a valid integer");
                scanner.next(); // Clear invalid input
            }
        }
    }

    /**
     * Prompts the user to input the ticket release rate and validates it.
     * Ensures that the entered value is a positive integer.
     *
     * @return ticketReleaseRate - the validated ticket release rate per 10 seconds
     */
    public static int validateTicketReleaseRate() {
        int ticketReleaseRate;
        while (true) {
            System.out.print("Enter Vendor Ticket Release Rate Per second: ");
            if (scanner.hasNextInt()) {
                ticketReleaseRate = scanner.nextInt();
                if (ticketReleaseRate > 0) {
                    return ticketReleaseRate;
                } else {
                    System.out.println("Error: Ticket Release Rate must be greater than 0");
                }
            } else {
                System.out.println("Error: Please enter a valid Integer number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    /**
     * Prompts the user to input the customer retrieval rate and validates it.
     * Ensures that the entered value is a positive integer.
     *
     * @return customerRetrievalRate - the validated customer retrieval rate per 10 seconds
     */
    public static int validateCustomerRetrievalRate() {
        int customerRetrievalRate;
        while (true) {
            System.out.print("Enter Customer Retrieval Rate Per second: ");
            if (scanner.hasNextInt()) {
                customerRetrievalRate = scanner.nextInt();
                if (customerRetrievalRate > 0) {
                    return customerRetrievalRate;
                } else {
                    System.out.println("Error: Customer Retrieval Rate must be greater than 0");
                }
            } else {
                System.out.println("Error: Please enter a valid Integer number");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
