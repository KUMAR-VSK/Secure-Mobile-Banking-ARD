# Secure Mobile Banking ARD

A simple banking system built using Spring Boot Microservices architecture.

## Microservices

| Microservice | Database | Description |
|---|---|---|
| Auth Service | `banking_auth` | User registration, login with JWT authentication |
| Account Service | `banking_account` | Account creation, balance check and update |
| Transaction Service | `banking_transaction` | Money transfer, transaction history, suspicious flag for amounts > 50000 |
| API Gateway | — | Routes all incoming requests to the respective microservice |

## Tech Stack

- **Framework**: Spring Boot
- **Database**: MySQL
- **ORM**: Spring Data JPA / Hibernate
- **Security**: Spring Security + JWT (jjwt)
- **Gateway**: Spring Cloud Gateway
- **API**: REST (JSON)

## Features

- User registration and login with JWT token
- Create bank account linked to user
- Check account balance
- Transfer money between accounts
- Save all transaction records
- Transactions with amount > 50000 are automatically flagged as suspicious

## Project Structure

```
banking-system/
├── pom.xml                    (Parent POM)
├── auth-service/              (Authentication microservice)
│   ├── src/main/java/.../
│   │   ├── AuthServiceApplication.java
│   │   ├── controller/AuthController.java
│   │   ├── entity/User.java
│   │   ├── repository/UserRepository.java
│   │   ├── service/AuthService.java
│   │   └── security/
│   │       ├── JwtUtil.java
│   │       └── SecurityConfig.java
│   └── src/main/resources/application.yml
├── account-service/           (Account management microservice)
│   ├── src/main/java/.../
│   │   ├── AccountServiceApplication.java
│   │   ├── controller/AccountController.java
│   │   ├── entity/Account.java
│   │   ├── repository/AccountRepository.java
│   │   └── service/AccountService.java
│   └── src/main/resources/application.yml
├── transaction-service/       (Transaction microservice)
│   ├── src/main/java/.../
│   │   ├── TransactionServiceApplication.java
│   │   ├── controller/TransactionController.java
│   │   ├── entity/Transaction.java
│   │   ├── repository/TransactionRepository.java
│   │   └── service/TransactionService.java
│   └── src/main/resources/application.yml
└── api-gateway/               (API Gateway)
    ├── src/main/java/.../
    │   └── ApiGatewayApplication.java
    └── src/main/resources/application.yml
```

## API Endpoints

### Auth Service
- `POST /api/auth/register` — Register a new user
- `POST /api/auth/login` — Login and receive JWT token

### Account Service
- `POST /api/accounts/create` — Create a new account
- `GET /api/accounts/balance/{accountNumber}` — Get account balance

### Transaction Service
- `POST /api/transactions/transfer` — Transfer money between accounts
- `GET /api/transactions/history/{accountNumber}` — Get transaction history
- `GET /api/transactions/suspicious` — Get all suspicious transactions

## How to Run

1. Ensure MySQL is running on localhost
2. Build the project: `mvn clean install`
3. Start each service: `mvn spring-boot:run` (from each module directory)
4. Send all requests through the API Gateway

## Database Configuration

Each service uses its own MySQL database (auto-created on startup):
- Auth Service → `banking_auth`
- Account Service → `banking_account`
- Transaction Service → `banking_transaction`

Default credentials: `root` / `root`

## Author

KUMAR-VSK
