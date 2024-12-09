package com.example.ShehansTicketBooking.Backend.Controller;

import com.example.ShehansTicketBooking.Backend.Enums.SystemStatus;
import com.example.ShehansTicketBooking.Backend.Models.Configuration;
import com.example.ShehansTicketBooking.Backend.Models.ThreadsManager;
import com.example.ShehansTicketBooking.Backend.Models.Tickets;
import com.example.ShehansTicketBooking.Backend.Repo.ConfigurationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/tickets")
public class TicketsController {
    private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);

    private final ConfigurationRepo configurationRepo;
    private ThreadsManager threadsManager;
    private SystemStatus systemStatus = SystemStatus.STOPPED; // Default status


    public TicketsController(ConfigurationRepo configurationRepo) {
        this.configurationRepo = configurationRepo;
    }

    @PostMapping("/start")
    public String startSystem() {
        Configuration latestConfig = configurationRepo.findLastConfiguration();
        if (latestConfig == null) {
            return "No configuration found. Please set up a configuration in the Admin Panel first.";
        }

        if (systemStatus == SystemStatus.RUNNING) {
            return "System is already running.";
        }

        // Initialize threadsManager with the latest configuration
        Tickets tickets = new Tickets(latestConfig.getMaxTicketCapacity(), latestConfig.getTotalTickets());
        threadsManager = new ThreadsManager(tickets, latestConfig);

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
        if (threadsManager == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "System is offline. Please start the system first.");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        logger.info("Monitoring System Status...");

        // Parse the status report into a structured JSON response
        Map<String, Object> statusResponse = threadsManager.getMonitorStatusAsJson();
        return ResponseEntity.ok(statusResponse);
    }

}
