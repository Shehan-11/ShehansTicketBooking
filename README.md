
# A Real Time Ticket Booking System

This is a project that is made using Java and Springboot for simulate real time ticket booking system.

Made with Springboot version 3.3.5

## How to run the System?
1st Go to com/example/ShehansTicketBooking/Backend/ShehansTicketBookingApplication.java then run the program.
Then Start the Front-end Application using given information.
## API Reference

#### Post a Configuration

```http
  POST /api/configuration/createConfig
```

* Sample JSON Package,
```
{
    "maxTicketCapacity": 200,
    "totalTickets": 5,
    "ticketReleaseRate": 1,
    "customerRetrievalRate": 1
}
```
#### Get Last Configuration

```http
  GET api/configuration/getLastConfig
```

#### Start Simulation

```http
  POST /api/tickets/start
```

* Send a Empty Body

#### Stop Simulation

```http
  POST /api/tickets/stop
```

* Send a Empty Body

#### Get Ticket Status

```http
  GET api/tickets/monitor
```

