# Trip Buddy API (Bus Ticketing & Reservation System)

A robust, enterprise-grade RESTful API built with Spring Boot for managing intercity bus routes, scheduling trips, and handling real-time seat reservations.

This project demonstrates core backend engineering principles, including the **DTO pattern**, **Global Exception Handling**, **Concurrency Management** (preventing double-bookings), and **Automated Background Tasks**.

## Key Features

*   **Concurrency-Safe Booking Engine:** Utilizes Database Pessimistic Locking (`@Lock(LockModeType.PESSIMISTIC_WRITE)`) to ensure multiple users cannot book the same seat simultaneously.
*   **Automated Seat Release (Janitor Task):** Implements Spring's `@Scheduled` tasks to automatically release `PENDING` seats back to `AVAILABLE` if a user does not complete their payment within a 10-minute hold window.
*   **Clean Architecture:** Strict separation of concerns using Data Transfer Objects (DTOs) to prevent entity leakage and secure data mutation.
*   **Global Exception Handling:** Uses `@RestControllerAdvice` to translate Java exceptions into clean, readable HTTP status codes (e.g., 404 Not Found, 409 Conflict).
*   **UUID Identifiers:** Enhances security and distributed system compatibility by using UUIDs for all primary database records instead of predictable sequential IDs.

## Tech Stack

*   **Language:** Java 17
*   **Framework:** Spring Boot 4.0.6
*   **Database:** Neon(PostgreSQL)
*   **Data Access:** Spring Data JPA
*   **Documentation:** OpenAPI 3.1.0 (Swagger)
*   **Build Tool:** Maven

## API Endpoints

### Route Management (`/api/v1/routes`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/` | Retrieve a list of all active routes. |
| `POST` | `/` | Create a new origin-to-destination route. |

###  Trip & Seat Management (`/api/v1/trips`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/` | Schedule a new trip on an existing route and initialize seats. |
| `GET` | `/search` | Search for available trips by origin, destination, and date. |
| `GET` | `/{tripId}/seats` | Fetch the real-time visual seat map (`AVAILABLE`, `LOCKED`, `BOOKED`). |

###  Booking Engine (`/api/v1/bookings`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/reserve` | Places a temporary 10-minute hold on a seat. Returns a 409 if already locked. |
| `POST` | `/{bookingId}/confirm` | Finalizes the booking by simulating payment and permanently booking the seat. |

## Database Schema Overview

*   **User:** Stores user details (Name, Email).
*   **Route:** Defines physical paths (Origin City, Destination City, Distance).
*   **Trip:** A specific scheduled instance of a Route (Departure time, Bus Plate, Base Price).
*   **Seat:** Represents individual physical seats mapped to a Trip.
*   **Booking:** The transactional record linking a User, Trip, and Seat with a specific state (`PENDING`, `CONFIRMED`, `CANCELLED`).

## Future Enhancements

*   **Authentication & Authorization:** Integrate **Spring Security** with JWT (JSON Web Tokens) to protect user-specific routes and introduce Admin vs. User roles.
*   **Real Payment Gateway:** Swap the mocked payment token system with a live Stripe or PayPal SDK integration.

## Development Roadmap

### Phase 1: Core Foundation (Complete)
- [x] Project Initialization & Git Setup
- [x] Entity creation (`User`, `Route`, `Trip`, etc.)
- [x] Basic CRUD operations for Routes and Trips
- [x] Search endpoints (by origin, destination, date)

### Phase 2: Booking Engine (Complete)
- [x] Create `Seat` and `Booking` entities
- [x] Seat generation logic per trip
- [x] View seat availability endpoint
- [x] Basic reservation endpoint

### Phase 3: Concurrency & Automation (Complete)
- [x] Implement pessimistic database locking (`@Lock`) for concurrent bookings
- [x] Add 10-minute hold logic for pending reservations
- [x] Implement Spring `@Scheduled` task to release expired holds
- [x] Global exception handling and data validation

## Local Development Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/raiyan-takrim/trip-buddy.git
   cd trip-buddy
   ```

2. **Database Configuration:**
    * Ensure PostgreSQL is running locally.
    * Create a database named `trip-buddy`.
    * Create an `application-dev.properties` file in `src/main/resources` with your local database credentials (this file is git-ignored).

3. **Run the application:**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
    ```
4. Access the Swagger UI documentation at `http://localhost:8080/swagger-ui.html`