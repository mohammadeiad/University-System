# University System

A web application built with **Spring Boot** for managing students, courses, enrollments, majors, semesters, faculty, and nationalities.

## Features

- Add, update, and delete students and courses
- Manage student enrollments
- Track student majors, semesters, faculty, and nationality

## Architecture

The project follows a **Model-Service-Repository** structure:  

- **Model** – defines the data entities.  
- **Repository** – handles database operations.  
- **Service** – contains an **interface** and its **implementation** for business logic.  

## Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / MySQL (or your DB choice)
- Maven / Gradle

## Project Structure

- `Student` – Manage student data  
- `Course` – Manage courses  
- `Enrollment` – Handle student enrollments  
- `Major` – Track student majors  
- `Semester` – Track semester info  
- `Faculty` – Track faculty info  
- `Nationality` – Track student nationality  

## Setup Instructions
