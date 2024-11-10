package com.example.ShehansTicketBooking.CLI.Config;
import java.sql.*;
import java.time.LocalDateTime;

public class ConfigurationManager {
    private static final String dataBaseURL = "jdbc:mysql://localhost:3306/oopDB";
    private static final String userName = "root";
    private static final String password = "root";

    // 01. Saving configuration to the dataBase
    public void saveConfiguration(Configuration config) {

        // Define the SQL INSERT query to add a new record to the config_details table.
        // (?) act as placeholders for parameters that will be set dynamically in the code below.
        String insertQuery = "INSERT INTO config_details (Max_Ticket_Capacity, Total_Tickets, Ticket_Release_Rate, " +
                "Customer_Retrieval_Rate, Last_Update_Date) VALUES (?, ?, ?, ?, ?)";

        // Use a try-with-resources block to automatically close the resources (connection and preparedStatement) when done
        try (Connection connection = DriverManager.getConnection(dataBaseURL, userName, password);
             // Prepare the SQL INSERT query
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            System.out.println("Connected to the Database Successfully");

            // Set the values for the placeholders in the INSERT query using the data from the 'config' object
            preparedStatement.setInt(1, config.getMaxTicketCapacity());
            preparedStatement.setInt(2, config.getTotalTickets());
            preparedStatement.setDouble(3, config.getTicketReleaseRate());
            preparedStatement.setDouble(4, config.getCustomerRetrievalRate());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now())); // Set the Current time to the Last_Update_Date Column

            // Execute the update query and get the number of affected rows
            int affectedRows = preparedStatement.executeUpdate();

            // Check if no rows were affected.
            if (affectedRows == 0) {
                // Throw an SQLException if no rows were affected, indicating a failure in saving the configuration
                throw new SQLException("Failed to save configuration.");
            }

            System.out.println("Configuration saved successfully");

        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // 02. Loads the most recent configuration from the database
    public Configuration loadLatestConfig() {
        String selectSQL = "SELECT * FROM Config_Details ORDER BY Last_Update_Date DESC LIMIT 1";

        // Establish a connection to the database, prepare the SQL statement, and execute the query in a try-with-resources block.
        // The try-with-resources ensures that the connection, preparedStatement, and resultSet are automatically closed after use.
        try (Connection connection = DriverManager.getConnection(dataBaseURL, userName, password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Check if the resultSet contains any rows; if so, retrieve the data for the latest configuration record.
            if (resultSet.next()) {
                int configID = resultSet.getInt("ConfigID");
                int maxTicketCapacity = resultSet.getInt("Max_Ticket_Capacity");
                int totalTickets = resultSet.getInt("Total_Tickets");
                double ticketReleaseRate = resultSet.getDouble("Ticket_Release_Rate");
                double customerRetrievalRate = resultSet.getDouble("Customer_Retrieval_Rate");
                LocalDateTime lastUpdateDate = resultSet.getTimestamp("Last_Update_Date").toLocalDateTime();

                Configuration config = new Configuration(configID, maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate, lastUpdateDate);
                return config;
            } else {
                System.out.println("No configuration found.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return null;
        }
    }
}
