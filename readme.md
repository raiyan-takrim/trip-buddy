# Intercity Bus Ticket Booking API (Trip Buddy)

A RESTful API for managing intercity bus routes, scheduling trips, and handling concurrent seat reservations. This project is currently under active development.

## Tech Stack

*   **Language:** Java 17
*   **Framework:** Spring Boot 3.x
*   **Database:** PostgreSQL
*   **Data Access:** Spring Data JPA / Hibernate
*   **Build Tool:** Maven

## Development Roadmap

### Phase 1: Core Foundation (In Progress)
- [x] Project Initialization & Git Setup
- [x] Entity creation (`User`, `Route`, `Trip`, etc.)
- [x] Basic CRUD operations for Routes and Trips
- [x] Search endpoints (by origin, destination, date)

### Phase 2: Booking Engine (Planned)
- [ ] Create `Seat` and `Booking` entities
- [x] Seat generation logic per trip
- [x] View seat availability endpoint
- [ ] Basic reservation endpoint

### Phase 3: Concurrency & Automation (Planned)
- [ ] Implement pessimistic database locking (`@Lock`) for concurrent bookings
- [ ] Add 10-minute hold logic for pending reservations
- [ ] Implement Spring `@Scheduled` task to release expired holds
- [ ] Global exception handling and data validation

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