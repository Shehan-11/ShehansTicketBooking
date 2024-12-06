package com.example.ShehansTicketBooking.Backend.Controller;

import com.example.ShehansTicketBooking.Backend.Enums.SystemStatus;
import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import com.example.ShehansTicketBooking.Backend.Models.ThreadsManager;
import com.example.ShehansTicketBooking.Backend.Models.Tickets;
import com.example.ShehansTicketBooking.Backend.Repo.ConfigurationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {
    private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);

    private final Configuration latestConfig;
    private final ThreadsManager threadsManager;
    private SystemStatus systemStatus = SystemStatus.STOPPED; // Default status


    public TicketsController(ConfigurationRepo configurationRepo) {
        this.latestConfig = configurationRepo.findLastConfiguration();
        if (latestConfig != null) {
            Tickets tickets = new Tickets(latestConfig.getMaxTicketCapacity(), latestConfig.getTotalTickets());
            this.threadsManager = new ThreadsManager(tickets, latestConfig);
        } else {
            this.threadsManager = null; // Handle the case where no configuration is found
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
        threadsManager.startSimulation();
        systemStatus = SystemStatus.RUNNING;
        return "System is Started";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        if (systemStatus == SystemStatus.STOPPED) {
            return "System is already stopped.";
        }

        threadsManager.stopSimulation();
        systemStatus = SystemStatus.STOPPED;
        logger.info("Simulation Stopped");
        return "System is Stopped";
    }

    @GetMapping("/monitor")
    public ResponseEntity<Map<String, Object>> monitorSystem() {
        if (systemStatus == SystemStatus.RUNNING) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "System is running. Stop and try again.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        logger.info("Monitoring System Status...");

        // Parse the status report into a structured JSON response
        Map<String, Object> statusResponse = threadsManager.getMonitorStatusAsJson();

        return ResponseEntity.ok(statusResponse);
    }

}
