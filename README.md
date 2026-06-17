# PlayStat API

Backend MVP for basketball player performance management.

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Bean Validation

## Features

- Create player
- List players
- Find player by ID
- Update player
- Delete player

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /players | Create player |
| GET | /players | List players |
| GET | /players/{id} | Find player by ID |
| PUT | /players/{id} | Update player |
| DELETE | /players/{id} | Delete player |

## Running locally

Create PostgreSQL database:

```sql
CREATE DATABASE playstat;
````
Configure database credentials in:
```sql
src/main/resources/application.properties
````
Run the application:
```sql
mvn spring-boot:run
````
