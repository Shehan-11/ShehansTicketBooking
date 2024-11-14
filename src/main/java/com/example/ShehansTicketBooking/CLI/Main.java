package com.example.ShehansTicketBooking.CLI;

import com.example.ShehansTicketBooking.CLI.Config.ConfigInputsAndValidations;
import com.example.ShehansTicketBooking.CLI.Config.ConfigurationManager;
import com.example.ShehansTicketBooking.CLI.Config.Configuration;
import com.example.ShehansTicketBooking.CLI.UserAccounts.UserNamePasswordValidation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting CLI Application...");
        System.out.println("""
                
                    ================================================
                    |     Welcome to the Ticket Booking System     |
                    ================================================
                """);
        menu();
    }

    public static void menu() {
        System.out.println("""
                              \s
                                 Please select an option
                    1) Log as an Admin
                    2) Log as a Customer
                    3) Log as a Vendor
                    9) Quit
                    =================================================
                  \s""");
        System.out.print("Select an option: ");
        Scanner scanner = new Scanner(System.in);
        try{
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    adminPanel();
                    menu();
                case 2:
//                    customerLogin();
                    menu();
                case 3:
//                    vendorLogin();
                    menu();
                case 9:
                    System.out.println();
                    System.out.println("Thank you! Have a nice day.");
                    System.exit(0);
                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    menu();
            }
            // Exception Handling
        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("Invalid data format!");
            menu();
        }
    }

    public static void adminPanel() {

        Scanner scanner = new Scanner(System.in);
        boolean loginStatus = false;

        while (!loginStatus) {
            System.out.println("""
                    
                    =======================
                    |     Admin Login     |
                    =======================
                    """);

            UserNamePasswordValidation adminLoginValidation = new UserNamePasswordValidation();

            System.out.print("Enter your User Name: ");
            adminLoginValidation.setUserName(scanner.next());
            System.out.print("Enter your Password: ");
            adminLoginValidation.setPassword(scanner.next());

            if (adminLoginValidation.verifyAdminLogin()) {
                System.out.println("User logged in successfully. Logged as: " + adminLoginValidation.getUserName().toUpperCase());
                boolean keepRunning = true;
                while (keepRunning) {
                    System.out.println("""
                        
                        ===============================
                        |     System Configuration    |
                        ===============================
                        
                        Please choose an option:
                        1. Load the current saved configuration
                        2. Create a new configuration
                        3. Go to Main Menu
                        """);

                    System.out.print("Enter your choice: ");

                    int choice = -1;
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter an valid integer");
                        scanner.next(); // Clear the invalid input
                        continue; // Restart the loop
                    }

                    switch (choice) {
                        case 1 -> {
                            ConfigurationManager configManager = new ConfigurationManager();
                            Configuration latestConfig = configManager.loadLatestConfig();
                            if (latestConfig != null) {
                                System.out.println("Loaded configuration: " + latestConfig);
                            } else {
                                System.out.println("No saved configuration found.");
                            }
                        }

                        case 2 -> {
                            int maxTicketCapacity = ConfigInputsAndValidations.validateMaxTicketCapacity();
                            int totalTickets = ConfigInputsAndValidations.validateTotalTickets(maxTicketCapacity);
                            double ticketReleaseRate = ConfigInputsAndValidations.validateTicketReleaseRate();
                            double customerRetrievalRate = ConfigInputsAndValidations.validateCustomerRetrievalRate();

                            Configuration config = new Configuration(maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate);
                            ConfigurationManager configManager = new ConfigurationManager();
                            configManager.saveConfiguration(config);
                        }

                        case 3 -> {
                            System.out.println("Returning to Main Menu...");
                            keepRunning = false; // Exit the loop to return to Main Menu
                        }
                        default -> System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                }

                loginStatus = true; // End login loop
            } else {
                System.out.println("Invalid username or password. Please try again.\n");
            }
        }
    }
}
