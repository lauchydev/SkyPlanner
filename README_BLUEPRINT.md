# Project Blueprint: SkyPlanner API
**Description:** A professional-grade RESTful API that integrates real-time weather data to assist users in travel planning, featuring secure user authentication and high-performance caching.

---

## 1. Technical Stack (All Free/Open Source)
*   **Language:** Java 17 or 21 (LTS versions)
*   **Framework:** Spring Boot 3.x
*   **Security:** Spring Security + JWT (JSON Web Tokens) for stateless authentication
*   **Database:** PostgreSQL (Relational data: Users, Saved Trips)
*   **Caching:** Redis (To cache weather responses and improve latency)
*   **External API:** OpenWeatherMap API (Free tier)
*   **Documentation:** SpringDoc OpenAPI (Swagger UI)
*   **Testing:** JUnit 5 & Mockito
*   **Containerization:** Docker & Docker Compose

---

## 2. Documentation Standards
### A. The README.md (The "Handshake")
1.  **Project Overview:** What does it do and why?
2.  **Tech Stack:** A clear list of tools used.
3.  **Prerequisites:** (e.g., Docker, Java JDK).
4.  **Setup Instructions:** One or two commands to get the app running.
5.  **API Documentation Link:** Instruction on how to access the Swagger UI.

### B. API Documentation (The "Manual")
We will use **Swagger/OpenAPI**. Your API will host an interactive webpage (usually at `/swagger-ui.html`) where users can test endpoints directly in their browser.

---

## 3. Development Phases (Roadmap)

### Phase 1: Infrastructure & Foundation
*   **Task 1.1:** Initialize Spring Boot project via Spring Initializr.
*   **Task 1.2:** Create `docker-compose.yml` for PostgreSQL and Redis.
*   **Task 1.3:** Implement "Health Check" endpoint.
*   **Task 1.4:** Define Core Entities (User, Trip).

### Phase 2: The Weather Engine (External Integration)
*   **Task 2.1:** Register for OpenWeatherMap API key.
*   **Task 2.2:** Create `WeatherService` using `WebClient`.
*   **Task 2.3:** Implement Error Handling (City not found, etc.).
*   **Task 2.4:** Create Controller for `/api/weather/{city}`.

### Phase 3: Security & Authentication
*   **Task 3.1:** Implement Spring Security configuration.
*   **Task 3.2:** Create User Registration and Login endpoints.
*   **Task 3.3:** Implement JWT Logic (Token generation & JwtFilter).
*   **Task 3.4:** Protect routes (e.g., `/api/trips` requires JWT).

### Phase 4: Business Logic & Caching
*   **Task 4.1:** Implement "Saved Trip" functionality linked to User.
*   **Task 4.2:** Integrate Redis Caching for weather responses.
*   **Task 4.3:** Ensure database foreign key relationships are correct.

### Phase 5: Testing, Documentation & Polishing
*   **Task 5.1:** Write Unit Tests (JUnit/Mockito) and Integration Tests.
*   **Task 5.2:** Finalize Swagger documentation with `@Operation` annotations.
*   **Task 5.3:** Code cleanup and optimization.

---

## 4. Instructions for AI Agents (Implementation Guide)
*Use these prompts when starting a new session:*

*   **Setup:** "I am starting Phase 1 of SkyPlanner. Help me write a `docker-compose.yml` file that includes PostgreSQL and Redis, and show me how to configure my `application.properties`."
*   **Security:** "I am in Phase 3. I need to implement JWT authentication. Explain the flow of how a user logs in and uses the token for authenticated requests."
*   **Integration:** "In Phase 2, help me write a Spring Service using `WebClient` that fetches weather data from OpenWeatherMap and maps it to a DTO."
