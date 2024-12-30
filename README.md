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
git clone <repository-url>
cd video-status-server
