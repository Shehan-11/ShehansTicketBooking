/**
 * This Class handles displaying the main menu options, Admin Panel,
 * and the Ticket Buy/Sell simulation for the Ticket Booking System.
 */

package com.example.ShehansTicketBooking.CLI.Menu;

import com.example.ShehansTicketBooking.CLI.Config.ConfigInputsAndValidation;
import com.example.ShehansTicketBooking.CLI.Config.Configuration;
import com.example.ShehansTicketBooking.CLI.DataBase.ConfigurationManager;
import com.example.ShehansTicketBooking.CLI.DataBase.UserNamePasswordValidation;
import com.example.ShehansTicketBooking.CLI.Ticket.TicketManager;
import com.example.ShehansTicketBooking.CLI.Ticket.TicketPool;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Displays the main menu and handles user selections.
 * Options:
 * 1 - Admin login
 * 2 - Start ticket simulation
 * 9 - Quit the program
 */
public class Menu {
    public static void menu() {
        System.out.println("""
                              \s
                                 Please select an option
                    1) Login as an Admin
                    2) Simulate Ticket Buy and Sell
                    9) Quit
                    =================================================
                  \s""");
        System.out.print("Select an option: ");
        Scanner scanner = new Scanner(System.in);
        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1 -> {
                    adminPanel(); // Open Admin Panel
                    menu(); // Redisplay main menu after returning
                }
                case 2 -> {
                    simulateTicketBuySell(); // Start Ticket Simulation
                    menu(); // Redisplay main menu after returning
                }
                case 9 -> {
                    System.out.println("\nThank you! Have a nice day.");
                    System.exit(0); // Exit program
                }
                default -> {
                    System.out.println("\nInvalid option. Please try again.");
                    menu(); // Redisplay main menu for invalid input
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid data format!");
            menu(); // Redisplay main menu for invalid input format
        }
    }

    /**
     * Admin login and configuration management panel.
     * Handles admin authentication and provides options to load,
     * create configurations, or return to the main menu.
     */
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

            if (adminLoginValidation.verifyAdminLogin()) { // Verify admin login
                System.out.println("User logged in successfully. Logged as: " + adminLoginValidation.getUserName().toUpperCase());
                boolean keepRunning = true;

                // Display configuration options for authenticated admin
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

                    int choice;
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
                            // Set new configuration details
                            int maxTicketCapacity = ConfigInputsAndValidation.validateMaxTicketCapacity();
                            int totalTickets = ConfigInputsAndValidation.validateTotalTickets(maxTicketCapacity);
                            int ticketReleaseRate = ConfigInputsAndValidation.validateTicketReleaseRate();
                            int customerRetrievalRate = ConfigInputsAndValidation.validateCustomerRetrievalRate();

                            // Save new configuration
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

    /**
     * Starts the ticket buying/selling simulation.
     * Provides options to start, stop, monitor ticket status, or return to the main menu.
     */
    public static void simulateTicketBuySell() {
        ConfigurationManager configManager = new ConfigurationManager();
        Configuration latestConfig = configManager.loadLatestConfig();

        if (latestConfig == null) { // Check for existing configuration
            System.out.println("No configuration found. Please set up a configuration in the Admin Panel first.");
            return;
        }

        TicketPool ticketPool = new TicketPool(latestConfig.getMaxTicketCapacity(), latestConfig.getTotalTickets());
        TicketManager ticketManager = new TicketManager(ticketPool, latestConfig);

        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        // Simulation options loop
        while (keepRunning) {
            System.out.println("""
                
                ==============================
                |     Ticket Buy and Sell    |
                ==============================
                
                Please choose an option:
                1. Start the Simulation
                2. Stop the Simulation
                3. Monitor Ticket Status
                9. Go Back to Main Menu
                """);

            System.out.print("Enter your choice (1, 2, 3, 9): ");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> ticketManager.startSimulation(); // Start the ticket simulation
                case 2 -> ticketManager.stopSimulation(); // Stop the simulation
                case 3 -> ticketManager.monitorStatus(); // Display current ticket status
                case 9 -> {
                    System.out.println("Returning to Main Menu...");
                    keepRunning = false; // Exit to main menu
                    ticketManager.stopSimulation(); // Ensure simulation stops
                }
                default -> System.out.println("Invalid choice. Please select 1, 2, 3, or 9.");
            }
        }
    }
}