# ClubMaster-Backend
Backend application for the ClubMaster system.

## Concept

ClubMaster is a web application for managing football clubs and their players.

The backend provides a RESTful API that allows authenticated users to manage:

- Football teams
- Players belonging to each team

The relationship between the entities is: 1:N

A team can have multiple players, while each player belongs to one team.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring Security (JWT Authentication)
- PostgreSQL
- Maven
- Docker

## Database

The project uses PostgreSQL.