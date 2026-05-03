# Mouxadimatoul Xidma API

Spring Boot backend for Moukhadimatoul Xidma. It provides JWT-secured REST APIs for reservations, delegations, residences, rooms, events, users, roles, responsables, accueillants, and statistics used by the Angular back-office app.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security with JWT
- Spring Data JPA
- PostgreSQL
- Springdoc OpenAPI / Swagger UI
- Maven Wrapper

## Requirements

- Java 17
- PostgreSQL
- Database named `mkdatabase`

Default local configuration is in `src/main/resources/application.yml`:

```yaml
server.port: 8081
spring.datasource.url: jdbc:postgresql://localhost:5432/mkdatabase
spring.datasource.username: postgres
spring.datasource.password: postgres
```

## Getting Started

```bash
./mvnw spring-boot:run
```

The API runs at:

```text
http://localhost:8081/api/v1
```

Swagger UI is available at:

```text
http://localhost:8081/swagger-ui.html
```

## Available Commands

```bash
./mvnw spring-boot:run   # Start the API locally
./mvnw test              # Run tests
./mvnw clean package     # Build the application jar
```

## Main Endpoints

- `POST /api/v1/auth/login`
- `/api/v1/reservations`
- `/api/v1/delegations`
- `/api/v1/residences`
- `/api/v1/chambres`
- `/api/v1/evenements`
- `/api/v1/utilisateurs`
- `/api/v1/stats`

## Project Structure

```text
src/main/java/com/touba/backend/controller   REST controllers and API contracts
src/main/java/com/touba/backend/service      Service interfaces and implementations
src/main/java/com/touba/backend/repository   Spring Data repositories
src/main/java/com/touba/backend/model        JPA entities
src/main/java/com/touba/backend/dto          API DTOs and request objects
src/main/resources/application.yml           Local runtime configuration
```

## Docker

```bash
./mvnw clean package
docker build -t mouxadimatoul-xidma-api .
docker run --rm -p 8081:8081 mouxadimatoul-xidma-api
```

## Development Notes

- Do not commit production secrets or real database credentials.
- Add tests when changing authentication, validators, services, or controller behavior.

## Contributing

- Start new work from the latest `main`.
- Use focused branch names such as `feature/reservation-export`, `fix/jwt-validation`, or `chore/update-readme`.
- Keep commits small and use clear messages, for example `feat: add reservation export`, `fix: validate expired JWT`, or `docs: update database setup`.
- Open pull requests with a short summary, related issue if available, and the commands used to verify the change.
