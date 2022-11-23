# Mediscreen

A learning exercise about separating features into microservices.

## Technical specifications:

The project uses the following technologies:
- Maven
- Java 11
- Spring Boot 2.7
- Thymeleaf
- Bootstrap 5
- MySQL
- MongoDB

## Development environment

There are 2 Spring Boot profiles available:
- **"dev" (default):** 
  - api-patient uses in-memory H2 database.
  - api-notes looks for MongoDB in localhost.
- **"prod":** 
  - api-patient uses mysql in a container named "db"
  - api-notes looks for MongoDB in a container named "mongo"

The databases can be filled with test data using CURL commands (see examples in [data-setup/data.sh](data-setup/data.sh)).

## Deployment

The application can be deployed using
- Docker
- Docker Compose

Note that the images are built in Java 19 for increased performance.

## Admin tools after deployment

The project includes two containers listening only in localhost:
- port 8084: Adminer for MySQL
- port 8085: Mongo Express to