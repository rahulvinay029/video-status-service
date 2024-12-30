# Video Status Server and Client Library

This project demonstrates:
- **Server:** Simulates a long-running video translation process using Server-Sent Events (SSE).
- **Client Library:** Allows users to interact with the server, fetch job statuses, and handle retries efficiently.
- **Integration Test:** Demonstrates the interaction between the server and the client library.

---

## Features

1. **Server-Sent Events (SSE):**
   - Provides real-time updates on job statuses (`pending`, `completed`, or `error`).

2. **Client Library:**
   - Handles communication with the server.
   - Includes retry logic to avoid overloading the server and optimize network calls.

3. **Integration Test:**
   - Spins up the server and uses the client library to demonstrate real-world usage.
   - Logs job statuses for debugging and verification.

---

## Prerequisites

- **Java**: Version 17 or higher
- **Maven**: Version 3.8+
- **Git**: Installed on your machine

---

## How to Build and Run

### 1. Clone the Repository

```bash
git clone https://github.com/rahulvinay029/video-status-service
cd video-status-server
```
### 2. Build the Project
Use Maven to build the project:
```bash
./mvnw clean install
```
### 3. Run the Server
```bash
./mvnw spring-boot:run
```
The server will be accessible at:

SSE Endpoint: http://localhost:8080/status

## Running Integration Tests
To validate the interaction between the server and the client library, run the integration tests:
```bash
./mvnw test
```
What Happens:
The test spins up the server.
The client library fetches job statuses from the server.
Logs the final status (e.g., completed or error).

## Example Logs
```bash
Simulating job status...
Job status is pending
Job status is pending
Job status set to completed

```
## Architecture Overview
### Server
Built with Spring Boot.
Uses SSE to emit real-time job status updates.
###Client
Built with Spring WebFlux for asynchronous communication.
Implements retry logic to handle network failures gracefully.
###Test
Uses Spring Boot Test and JUnit 5 for integration testing.

## Project Structure
```bash
video-status-server/
├── src/main/java
│   ├── com/example/video_status_server/
│   │   ├── VideoStatusServerApplication.java  # Main Spring Boot Application
│   │   ├── controllers/
│   │   │   └── StatusController.java         # SSE Endpoint
│   │   ├── services/
│   │       └── JobSimulationService.java     # Job Simulation Logic
│   ├── com/example/video_status_client/
│       ├── VideoStatusClient.java            # Client Interface
│       └── VideoStatusClientImpl.java        # Client Implementation
├── src/test/java
│   ├── com/example/video_status_server/
│   │   └── IntegrationTest.java              # Integration Test
└── pom.xml                                   # Maven Configuration


```
## Future Enhancements
1. **Enhanced Client Features:**
   - Add configurable polling intervals and backoff strategies.

2. **Error Handling:**
   - Implement detailed custom exceptions for network and server issues.
   
3. **Monitoring:**
   - Include metrics and logging for server performance.
