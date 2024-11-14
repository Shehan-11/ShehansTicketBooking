package com.example.ShehansTicketBooking.CLI.UserAccounts;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserNamePasswordValidation {
    private String userName;
    private String password;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = passwordEncryption(password);
    }

    public String getUserName(){
        return userName;
    }

    public String passwordEncryption(String password) {
        try {
            // Create a MessageDigest instance using the SHA-256 hashing algorithm
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Convert the password string into bytes and hash it
            byte[] hashedBytes = messageDigest.digest(password.getBytes());

            // Initialize a StringBuilder to hold the hexadecimal representation of the hashed bytes
            StringBuilder stringBuilder = new StringBuilder();

            // Loop through each byte in the hashed byte array
            for (byte b : hashedBytes) {
                // Convert each byte to a two-digit hexadecimal value and append it to the StringBuilder
                stringBuilder.append(String.format("%02x", b));
            }

            // Return the resulting hashed password in hexadecimal format as a string
            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            // Print an error message if the hashing algorithm is not found
            System.out.println("Hashing algorithm not found.");
            return null; // Return null if an error occurs during hashing
        }
    }

    // Method to verify Admin login details by comparing with database-stored hash Password
    public boolean verifyAdminLogin() {
        final String dataBaseURL = "jdbc:mysql://localhost:3306/oopDB";
        final String DbUserName = "root";
        final String DbPassword = "root";

        String query = "SELECT Password FROM admin_details WHERE User_Name = ?";

        try (Connection connection = DriverManager.getConnection(dataBaseURL, DbUserName, DbPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedPasswordHash = resultSet.getString("Password");
                return this.password.equals(storedPasswordHash);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
