/***
 * This is the Main Class of the System.
 */
package com.example.ShehansTicketBooking.CLI;

import static com.example.ShehansTicketBooking.CLI.Menu.Menu.menu;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting CLI Application...");
        System.out.println("""
                
                    ================================================
                    |     Welcome to the Ticket Booking System     |
                    ================================================
                """);

        // Calling the Menu Method
        menu();
    }
}
