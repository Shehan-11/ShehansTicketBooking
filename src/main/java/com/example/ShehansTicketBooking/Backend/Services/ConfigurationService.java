package com.example.ShehansTicketBooking.Backend.Services;

import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import com.example.ShehansTicketBooking.Backend.Repo.ConfigurationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfigurationService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    @Autowired
    private ConfigurationRepo configurationRepo;

    public Configuration createConfiguration(Configuration configuration) {
        // Ensure system time is set
        logger.info("Setting the Last Updated Time To System Date Time");
        if (configuration.getLastUpdateDate() == null) {
            configuration.setLastUpdateDate(LocalDateTime.now());
        }
        return configurationRepo.save(configuration);
    }
}
