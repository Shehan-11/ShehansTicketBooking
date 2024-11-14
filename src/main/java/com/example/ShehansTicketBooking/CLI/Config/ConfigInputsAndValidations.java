package com.example.ShehansTicketBooking.CLI.Config;

import java.util.Scanner;

public class ConfigInputsAndValidations {
    private static final Scanner scanner = new Scanner(System.in);

    // This Method is used to take input for tha Max Ticket and Validate it
    public static int validateMaxTicketCapacity() {
        int maxTicketCapacity;
        while (true) {
            System.out.println();
            System.out.print("Enter Max Ticket Capacity for the System: ");
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

    /*
    This Method is used to take input for tha Total Tickets and Validate it. This method takes a maxTicketCapacity as a
    Parameter to compare Total Tickets with Max Tickets
     */
    public static int validateTotalTickets(int maxTicketCapacity) {
        int totalTickets;
        while (true) {
            System.out.print("Enter Total Tickets Available in the System: ");
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

    // This Method is used to take input for Ticket Release Rate and Validate it
    public static double validateTicketReleaseRate() {
        double ticketReleaseRate;
        while (true) {
            System.out.print("Enter Ticket Release Rate (s): ");
            if (scanner.hasNextDouble()) {
                ticketReleaseRate = scanner.nextDouble();
                if (ticketReleaseRate > 0) {
                    return ticketReleaseRate;
                } else {
                    System.out.println("Error: Ticket Release Rate must be greater than 0");
                }
            } else {
                System.out.println("Error: Please enter a valid decimal number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // This Method is used to take input for Customer Retrieval Rate and Validate it
    public static double validateCustomerRetrievalRate() {
        double customerRetrievalRate;
        while (true) {
            System.out.print("Enter Customer Retrieval Rate (s): ");
            if (scanner.hasNextDouble()) {
                customerRetrievalRate = scanner.nextDouble();
                if (customerRetrievalRate > 0) {
                    return customerRetrievalRate;
                } else {
                    System.out.println("Error: Customer Retrieval Rate must be greater than 0");
                }
            } else {
                System.out.println("Error: Please enter a valid decimal number");
                scanner.next(); // Clear invalid input
            }
        }
    }
}
