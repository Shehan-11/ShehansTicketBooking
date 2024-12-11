# Shehan's Ticket Booking System

## 🎫 Project Overview

Shehan's Ticket Booking System is a web application designed to simulate the ticket booking process. This project provides an efficient and user-friendly platform for managing tickets, adding tickets and purchasing the tickets.

### Key Features
- Easy to setup
- User-friendly Configuration interface
- Real-time ticket status
- Easy to Start and Stop the Simulation
- Comprehensive backend with Spring Boot
- Modern frontend using React
- Database persistence with MySQL

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Spring Boot 3.3.5
- Maven
- MySQL Database
- React 19+
- Node.js 16+ and npm

### System Requirements
- Operating System: Windows, macOS, or Linux
- RAM: 8GB minimum (16GB recommended)
- Disk Space: 2GB free space

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Shehan-11/ShehansTicketBooking.git
   cd ShehansTicketBooking
   ```

2. Configure Database
   - Create a MySQL database named `oopdb`
   - Update database credentials in `src/main/resources/application.properties`
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/oopdb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Build the Project
   ```bash
   mvn clean install
   ```

4. Run the Application
   ```bash
   mvn spring-boot:run
   ```

5. Access the Application
   - Open your browser and navigate to `http://localhost:8080`

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.3.5
- **Java Version**: JDK 17
- **Dependencies**:
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Web
  - MySQL Connector
  - Spring Boot DevTools

### Frontend
- **Framework**: React 19
- **Key Libraries**:
  - React Router DOM (v7)
  - Axios for HTTP requests
  - Chart.js for data visualization
  - Bootstrap 5 for styling

### Database
- MySQL Database

### Build Tools
- Maven (Backend)
- npm (Frontend)

### Backend Startup
1. Navigate to `com/example/ShehansTicketBooking/Backend/ShehansTicketBookingApplication.java`
2. Run the Spring Boot application

### Frontend Startup
Follow the Create React App standard procedures:

#### Available Scripts

##### `npm start`
- Runs the app in development mode
- Access at [http://localhost:3000](http://localhost:3000)
- Automatic page reloads on changes
- Lint errors displayed in console

##### `npm test`
- Launches test runner in interactive watch mode
- Detailed test running information available at [Create React App Testing](https://facebook.github.io/create-react-app/docs/running-tests)

##### `npm run build`
- Builds production-ready app in `build` folder
- Optimizes React for best performance
- Minifies and hash-names files
- Ready for deployment
- Deployment details at [Create React App Deployment](https://facebook.github.io/create-react-app/docs/deployment)

##### `npm run eject`
**Note: Irreversible one-way operation**
- Removes single build dependency
- Copies configuration files and transitive dependencies
- Provides full control over webpack, Babel, ESLint configurations
- Use cautiously, as this action cannot be undone

## 🔧 API Reference

### Configuration Endpoints

#### Create Configuration
```http
POST /api/configuration/createConfig
```
**Sample Configuration JSON**:
```json
{
    "maxTicketCapacity": 200,
    "totalTickets": 5,
    "ticketReleaseRate": 1,
    "customerRetrievalRate": 1
}
```

#### Retrieve Last Configuration
```http
GET /api/configuration/getLastConfig
```

### Simulation Control Endpoints

#### Start Simulation
```http
POST /api/tickets/start
```
- Requires empty request body

#### Stop Simulation
```http
POST /api/tickets/stop
```
- Requires empty request body

#### Monitor Ticket Status
```http
GET /api/tickets/monitor
```

## 📦 Project Structure

```
ShehansTicketBooking/
│
├── backend/                # Backend system for managing data and business logic
│   ├── .mvn/               # Maven wrapper files for project management
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/ShehansTicketBooking/
│   │   │   │   ├── Backend/
│   │   │   │   │   ├── Controller/
│   │   │   │   │   │   ├── ConfigurationController.java    # Handles configurations API endpoints
│   │   │   │   │   │   └── TicketsController.java          # Handles ticket-related API endpoints
│   │   │   │   │   ├── Enums/
│   │   │   │   │   │   └── SystemStatus.java               # Enum for system statuses
│   │   │   │   │   ├── Exceptions/
│   │   │   │   │   │   └── ResourceNotFoundException.java  # Custom exception for missing resources
│   │   │   │   │   ├── Models/
│   │   │   │   │   │   ├── Configuration.java              # Model for system configurations
│   │   │   │   │   │   ├── Customers.java                  # Model for customer details
│   │   │   │   │   │   ├── ThreadsManager.java             # Manages threads for ticketing
│   │   │   │   │   │   ├── TicketPool.java                 # Manages ticket availability
│   │   │   │   │   │   └── Vendors.java                    # Model for vendor information
│   │   │   │   │   ├── Repo/
│   │   │   │   │   │   └── ConfigurationRepo.java          # Repository for accessing configurations
│   │   │   │   │   ├── Services/
│   │   │   │   │   │   └── ConfigurationService.java       # Service layer for configurations logic
│   │   │   │   │   └── ShehansTicketBookingApplication.java # Main application entry point
│   │   │   └── CLI/                                        # Command Line Interface implementation
│   │   └── resources/                                      # Application resources
│   └── test/java/com/example/ShehansTicketBooking/         # Unit and integration tests
│   └── pom.xml                                             # Maven project configuration
│
├── frontend/               # Frontend system for user interface
│   ├── .idea/              # IDE configuration files
│   ├── public/             # Public assets (e.g., static files)
│   ├── src/
│   │   ├── layout/
│   │   │   └── Navbar.js                      # Navigation bar component
│   │   ├── pages/
│   │   │   ├── Home.js                        # Homepage component
│   │   │   └── Tickets.js                     # Ticket listing component
│   │   ├── App.css                            # Global CSS styles
│   │   ├── App.js                             # Main application component
│   │   ├── App.test.js                        # Tests for App.js
│   │   ├── index.css                          # CSS for index page
│   │   ├── index.js                           # Entry point for React app
│   │   ├── logo.svg                           # Application logo
│   │   ├── reportWebVitals.js                 # Web performance reporting
│   │   └── setupTests.js                      # Setup for unit testing
│   ├── package-lock.json                      # Dependency lock file
│   ├── package.json                           # Node.js project configuration
│   └── README.md                              # Documentation for frontend
│
├── .gitignore             # Git configuration to ignore unnecessary files
├── README.md              # Project documentation
└── mvnw, mvnw.cmd         # Maven wrapper scripts

```

## 🧪 Testing

### Backend Testing
- Run backend tests:
  ```bash
  mvn test
  ```

### Frontend Testing
- Run frontend tests:
  ```bash
  npm test
  ```

### Code Style
- Follow Java and React community best practices
- Use meaningful variable and function names
- Write clean, documented, and performant code

## 📝 Development Considerations

### Create React App Notes
- Curated feature set for small to medium deployments
- No obligation to use `eject`
- Customization is possible when ready

## 📞 Contact & Support

Project Maintainer: Shehan Etulgama
- GitHub: [@Shehan-11](https://github.com/Shehan-11)

---

**Happy Ticket Booking! 🎉**
