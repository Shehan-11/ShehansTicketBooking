package com.example.ShehansTicketBooking.Backend.Repo;

import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepo extends JpaRepository<Configuration, Long> {
    // Query to get the last inserted Configuration
    @Query(value = "SELECT * FROM Config_Details ORDER BY Last_Update_Date DESC LIMIT 1", nativeQuery = true)
    Configuration findLastConfiguration();
}
