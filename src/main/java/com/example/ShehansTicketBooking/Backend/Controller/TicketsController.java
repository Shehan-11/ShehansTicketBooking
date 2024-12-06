package com.example.ShehansTicketBooking.Backend.Controller;

import com.example.ShehansTicketBooking.Backend.Enums.SystemStatus;
import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import com.example.ShehansTicketBooking.Backend.Models.Tickets;
import com.example.ShehansTicketBooking.Backend.Models.TicketsManager;
import com.example.ShehansTicketBooking.Backend.Repo.ConfigurationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {
    private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);

    private final Configuration latestConfig;
    private final TicketsManager ticketsManager;
    private SystemStatus systemStatus = SystemStatus.STOPPED; // Default status


    public TicketsController(ConfigurationRepo configurationRepo) {
        this.latestConfig = configurationRepo.findLastConfiguration();
        if (latestConfig != null) {
            Tickets tickets = new Tickets(latestConfig.getMaxTicketCapacity(), latestConfig.getTotalTickets());
            this.ticketsManager = new TicketsManager(tickets, latestConfig);
        } else {
            this.ticketsManager = null; // Handle the case where no configuration is found
        }
    }

    @PostMapping("/start")
    public String startSystem() {
        if (latestConfig == null) {
            return "No configuration found. Please set up a configuration in the Admin Panel first.";
        }
        if (systemStatus == SystemStatus.RUNNING) {
            return "System is already running.";
        }

        logger.info("Simulation Started");
        ticketsManager.startSimulation();
        systemStatus = SystemStatus.RUNNING;
        return "System is Started";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        if (systemStatus == SystemStatus.STOPPED) {
            return "System is already stopped.";
        }

        ticketsManager.stopSimulation();
        systemStatus = SystemStatus.STOPPED;
        logger.info("Simulation Stopped");
        return "System is Stopped";
    }

    @GetMapping("/monitor")
    public String monitorSystem() {
        if (systemStatus == SystemStatus.RUNNING) {
            return "System is running. Stop and Try Again.";
        }
        logger.info("Monitoring System Status...");
        return ticketsManager.monitorStatus();
    }

}
