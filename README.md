# ClubMaster Backend

Backend REST API for **ClubMaster**, a full-stack application designed to help manage sports clubs, their members, teams, and related club operations.

The backend is built with **Java and Spring Boot**, follows a layered architecture, and provides secure RESTful endpoints backed by **PostgreSQL**.

---

## 🚀 Overview

ClubMaster is a full-stack web application built to simplify the management of sports club operations.

The backend is responsible for:

* User authentication and authorization
* User and club management
* Member management
* Team-related operations
* Persistent data storage
* REST API communication with the frontend
* Secure access to protected resources

The frontend application is available here:

**ClubMaster Frontend:**
https://github.com/grylliasg/ClubMaster-Frontend

---

## 🛠️ Tech Stack

### Backend

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Data JPA**
* **Spring Security**
* **JWT Authentication**
* **Maven**

### Database

* **PostgreSQL**
* **Hibernate / JPA**

### Infrastructure

* **Docker**
* **Docker Compose**

### Development

* Git
* REST APIs
* JSON
* Maven

---

## 🏗️ Architecture

The application follows a layered backend architecture:

```text
┌─────────────────────────────┐
│          Client             │
│     React Frontend          │
└──────────────┬──────────────┘
               │
               │ HTTP / REST
               ▼
┌─────────────────────────────┐
│        Spring Boot          │
│                             │
│  ┌───────────────────────┐  │
│  │     Controllers       │  │
│  └───────────┬───────────┘  │
│              ▼              │
│  ┌───────────────────────┐  │
│  │       Services        │  │
│  └───────────┬───────────┘  │
│              ▼              │
│  ┌───────────────────────┐  │
│  │     Repositories      │  │
│  └───────────┬───────────┘  │
└──────────────┼──────────────┘
               ▼
┌─────────────────────────────┐
│         PostgreSQL          │
└─────────────────────────────┘
```

The main responsibility of each layer is:

### Controllers

Handle HTTP requests and expose the REST API.

### Services

Contain the application's business logic and coordinate operations between controllers and repositories.

### Repositories

Provide database access using Spring Data JPA.

### Entities

Represent the application's persistent domain model.

### Security

Spring Security and JWT are used to protect authenticated resources and control access to the API.

---

## 🔐 Authentication & Security

ClubMaster uses **Spring Security** together with **JSON Web Tokens (JWT)** for authentication.

The general authentication flow is:

```text
Client
  │
  │ Login credentials
  ▼
Authentication Endpoint
  │
  ▼
Spring Security
  │
  ▼
JWT Token
  │
  ▼
Client
  │
  │ Authorization: Bearer <token>
  ▼
Protected API Endpoints
```

This allows the backend to authenticate users and protect resources that require authorization.

---

## 🗄️ Database

The application uses **PostgreSQL** as its relational database.

Persistence is handled through:

* Spring Data JPA
* Hibernate
* JPA entities
* Repository abstractions

The database stores the application's core domain data and maintains relationships between the different entities.

---

## 📡 REST API

The backend exposes RESTful endpoints consumed by the ClubMaster frontend.

Typical API responsibilities include:

```text
Authentication
Users
Clubs
Members
Teams
```

The API communicates using JSON.

Example request:

```http
POST /api/...
Content-Type: application/json
Authorization: Bearer <JWT>
```

Example response:

```json
{
  "id": 1,
  "name": "Example",
  "status": "ACTIVE"
}
```

> The exact available endpoints can be found in the controller classes and can be explored through the application's API configuration.

---

## 🐳 Running with Docker

Docker can be used to simplify the local development environment.

### Prerequisites

Make sure you have installed:

* Docker
* Docker Compose
* Git

Clone the repository:

```bash
git clone https://github.com/grylliasg/ClubMaster-Backend.git
```

Navigate to the project:

```bash
cd ClubMaster-Backend
```

Start the application:

```bash
docker compose up --build
```

The exact configuration may depend on the environment variables and Docker Compose configuration included in the repository.

---

## 💻 Running Locally

### Prerequisites

* Java
* Maven
* PostgreSQL

Clone the repository:

```bash
git clone https://github.com/grylliasg/ClubMaster-Backend.git
cd ClubMaster-Backend
```

Build the project:

```bash
./mvnw clean package
```

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows, you can use:

```bash
mvnw.cmd spring-boot:run
```

Make sure your PostgreSQL instance and required application configuration are available before starting the application.

---

## ⚙️ Configuration

Application configuration is managed through Spring Boot configuration files and environment variables.

For local development, configure the required database connection and authentication settings.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/clubmaster
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

> Do not commit real credentials, JWT secrets, or other sensitive configuration to the repository.

---

## 📁 Project Structure

The project follows a conventional Spring Boot structure:

```text
src/
├── main/
│   ├── java/
│   │   └── ...
│   │
│   └── resources/
│       └── ...
│
└── test/
    └── ...
```

The backend code is organized around the application's domain and responsibilities, with dedicated components for:

```text
Controllers
Services
Repositories
Entities
Security
Configuration
DTOs
```

---

## 🧪 Testing

The project includes a test structure for validating backend functionality.

Tests can be executed with:

```bash
./mvnw test
```

or on Windows:

```bash
mvnw.cmd test
```

---

## 🔄 Development Workflow

The project is developed using Git for version control.

A typical workflow is:

```text
Feature
   ↓
Implementation
   ↓
Testing
   ↓
Git Commit
   ↓
Integration
```

The repository's commit history documents the development of the application over time.

---

## 🎯 Project Goals

The main goals of ClubMaster are:

* Build a practical full-stack application
* Design a maintainable Spring Boot backend
* Implement secure authentication
* Work with relational databases
* Expose a RESTful API
* Connect a modern frontend to a Java backend
* Practice real-world software development workflows
* Containerize the application for easier development and deployment

---

## 🔮 Future Improvements

Possible future improvements include:

* API documentation with OpenAPI / Swagger
* More comprehensive unit and integration tests
* Improved exception handling and API error responses
* Centralized logging
* CI/CD pipeline
* Production deployment
* Database migration management
* Monitoring and observability
* Performance and load testing
* Automated security testing

---

## 👨‍💻 Author

**Gryllias G.**

GitHub:
https://github.com/grylliasg

---

## 📄 License

This project is intended as a software engineering portfolio project.

If you plan to reuse or distribute the project, please check the repository's license and applicable project terms.
