# Salon Booking System Backend

## Student Information

* **Name:** Rutagengwa Ingabire Divine
* **Student ID:** 27482
* **Course:** INSY 8322 – Web Technology and Internet
* **Project:** Salon Booking System

## Project Description

The Salon Booking System is a Spring Boot REST API that manages salon operations, including customers, stylists, salon services, stations, bookings, and service records.

The application supports booking management, appointment status updates, searching, filtering, and dashboard statistics.

## Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Postman

## Database Configuration

The application uses PostgreSQL.

Create a database named:

```sql
CREATE DATABASE salon_mid_db;
```

Configure your PostgreSQL username and password in `src/main/resources/application.properties` before running the application.

## How to Run the Project

1. Clone this repository.
2. Open the project in IntelliJ IDEA.
3. Make sure PostgreSQL is running.
4. Create the `salon_mid_db` database.
5. Check the database credentials in `application.properties`.
6. Open the terminal in the project directory.
7. Run:

```bash
mvn clean install
```

8. Start the application:

```bash
mvn spring-boot:run
```

The application runs at:

```text
http://localhost:8082
```

## Implemented Requirements

### Part A – Basic CRUD Operations

* Entity management
* Repository implementation
* Service layer
* REST controllers

### Part B – Derived Queries

* Customers searched by last name
* Bookings searched by status
* Bookings searched between dates

### Part C – JPQL Queries

* Stylists by salon service
* Free stations
* Unused salon services
* Customers of a stylist

### Part D – Booking and Stylist Management

* Booking validation
* Booking status transitions
* Assigning salon services to stylists
* Assigning stations to stylists

### Part E – Service Records

* Creating service records
* Retrieving customer service history
* Deleting service records
* Protecting bookings that have service records

### Part F – Advanced Booking Operations

* Booking statistics grouped by status
* Bulk cancellation of bookings for a stylist on a specific date

### Bonus Requirements

* Dashboard summary
* Booking pagination and sorting

## Postman Collection

The Postman collection containing the API requests for testing the project is included in the root directory:

`salon-booking-system-midterm.postman_collection.json`

Import this file into Postman to test the implemented endpoints.


