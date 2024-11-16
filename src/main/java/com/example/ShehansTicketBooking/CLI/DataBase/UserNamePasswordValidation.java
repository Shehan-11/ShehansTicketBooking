/**
 * This class is used to handle username and password validation, including password hashing
 * and database verification for login purposes.
 */

package com.example.ShehansTicketBooking.CLI.DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserNamePasswordValidation {
    private String userName;
    private String password;

    // Setter for the userName field
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Setter for the password field with encryption
    public void setPassword(String password) {
        this.password = passwordEncryption(password); // Encrypt password before setting
    }

    // Getter for the userName field
    public String getUserName() {
        return userName;
    }

    /**
     * Encrypts a given password using the SHA-256 hashing algorithm.
     *
     * @param password The plain-text password to hash.
     * @return The hashed password as a hexadecimal string.
     */
    public String passwordEncryption(String password) {
        try {
            // Create a MessageDigest instance using the SHA-256 hashing algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convert the password string into bytes and hash it
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            // Initialize a StringBuilder to hold the hexadecimal representation of the hashed bytes
            StringBuilder stringBuilder = new StringBuilder();

            // Convert each byte to a two-digit hexadecimal value and append it to the StringBuilder
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            // Return the resulting hashed password in hexadecimal format
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hashing algorithm not found.");
            return null; // Return null if an error occurs during hashing
        }
    }

    /**
     * Verifies login details by comparing the provided credentials against the stored hash in the database.
     *
     * @param query The SQL query string to retrieve the stored password for comparison.
     * @return True if the stored hash matches the provided password hash, false otherwise.
     */
    private boolean verifyLogin(String query) {
        String dataBaseURL = "jdbc:mysql://localhost:3306/oopDB";
        String dbUserName = "root";
        String dbPassword = "root";

        // Connect to the database and execute the query to retrieve the stored password hash
        try (Connection connection = DriverManager.getConnection(dataBaseURL, dbUserName, dbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, userName); // Set username parameter in query
            ResultSet resultSet = statement.executeQuery(); // Execute query

            // Check if the result contains a matching row
            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("Password"); // Retrieve stored password hash
                return this.password.equals(storedPasswordHash); // Compare hashes
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print error details if an exception occurs
        }
        return false; // Return false if login verification fails
    }

    /**
     * Verifies Admin login details by checking if the stored password hash matches the provided hash.
     *
     * @return True if login is successful, false otherwise.
     */
    public boolean verifyAdminLogin() {
        String query = "SELECT Password FROM admin_details WHERE User_Name = ?"; // Query to select admin password hash
        return verifyLogin(query); // Call verifyLogin with admin-specific query
    }
}
