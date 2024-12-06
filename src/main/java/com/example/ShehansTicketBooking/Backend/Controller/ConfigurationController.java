package com.example.ShehansTicketBooking.Backend.Controller;

import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import com.example.ShehansTicketBooking.Backend.Repo.ConfigurationRepo;
import com.example.ShehansTicketBooking.Backend.Services.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);
    @Autowired
    private ConfigurationRepo configurationRepo;
    @Autowired
    private ConfigurationService configurationService;

    // Create a Configuration
    @PostMapping("/createConfig")
    public Configuration createConfiguration(@RequestBody Configuration configuration){
        logger.info("Creating a New Configuration File");
        return configurationService.createConfiguration(configuration);
    }

    // Get Latest Configuration from the Database
    @GetMapping("/getLastConfig")
    public Configuration lastConfig(){
        logger.info("Get the Last Configuration");
        return configurationRepo.findLastConfiguration();
    }
}
