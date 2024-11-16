/**
 * This class is used to manage configurations by saving and retrieving them from the database.
 */

package com.example.ShehansTicketBooking.CLI.DataBase;

import com.example.ShehansTicketBooking.CLI.Config.Configuration;

import java.sql.*;
import java.time.LocalDateTime;

public class ConfigurationManager {
    private static final String dataBaseURL = "jdbc:mysql://localhost:3306/oopDB"; // Database URL
    private static final String userName = "root"; // Database username
    private static final String password = "root"; // Database password

    /**
     * Saves the provided configuration object to the database.
     *
     * @param config The Configuration object containing settings to save.
     */
    public void saveConfiguration(Configuration config) {
        // SQL query to insert a new configuration record into the config_details table
        String insertQuery = "INSERT INTO config_details (Max_Ticket_Capacity, Total_Tickets, Ticket_Release_Rate, " +
                "Customer_Retrieval_Rate, Last_Update_Date) VALUES (?, ?, ?, ?, ?)";

        // Establish a connection to the database and prepare the SQL statement
        try (Connection connection = DriverManager.getConnection(dataBaseURL, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            System.out.println("Connected to the Database Successfully");

            // Set values in the SQL query using the provided configuration details
            preparedStatement.setInt(1, config.getMaxTicketCapacity());
            preparedStatement.setInt(2, config.getTotalTickets());
            preparedStatement.setInt(3, config.getTicketReleaseRate());
            preparedStatement.setInt(4, config.getCustomerRetrievalRate());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now())); // Set current timestamp

            // Execute the query and check if any rows were affected
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save configuration.");
            }

            System.out.println("Configuration saved successfully");

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage()); // Handle any exceptions and print error message
        }
    }

    /**
     * Loads the most recent configuration from the database.
     *
     * @return The latest Configuration object, or null if no configuration is found.
     */
    public Configuration loadLatestConfig() {
        // SQL query to select the most recent configuration record
        String selectSQL = "SELECT * FROM Config_Details ORDER BY Last_Update_Date DESC LIMIT 1";

        // Execute the query and retrieve the latest configuration record
        try (Connection connection = DriverManager.getConnection(dataBaseURL, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Check if any configuration records were retrieved from the database
            if (resultSet.next()) {
                int configID = resultSet.getInt("ConfigID");
                int maxTicketCapacity = resultSet.getInt("Max_Ticket_Capacity");
                int totalTickets = resultSet.getInt("Total_Tickets");
                int ticketReleaseRate = resultSet.getInt("Ticket_Release_Rate");
                int customerRetrievalRate = resultSet.getInt("Customer_Retrieval_Rate");
                LocalDateTime lastUpdateDate = resultSet.getTimestamp("Last_Update_Date").toLocalDateTime();

                // Create and return a Configuration object with the retrieved details
                return new Configuration(configID, maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate, lastUpdateDate);
            } else {
                System.out.println("No configuration found."); // Message if no records found
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage()); // Handle SQL exceptions
            return null;
        }
    }
}
