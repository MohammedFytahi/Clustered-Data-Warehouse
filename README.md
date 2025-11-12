# Clustered-Data-Warehouse📈

FX Cluster is a high-performance and scalable FX (Foreign Exchange) deal processing service built using modern Spring Boot practices. It provides robust validation, precise logging, and reliable persistence for financial deal records. Designed for banks, trading systems, and fintech platforms, FX Cluster ensures deal integrity, uniqueness, and extensibility.

![Project Status](https://img.shields.io/badge/Status-Production--Ready-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Coverage](https://img.shields.io/badge/Coverage-90%25-brightgreen)


---

## 🚀 Features

### ✅ Deal Ingestion
- Accepts FX deals via RESTful API.
- Converts DTOs to persistent entities via a clean mapper layer.

### 🛡️ Validation Layer
- Validates positive amounts and supported ISO currency codes.
- Ensures source and destination currencies differ.

### 🔁 Duplicate Detection
- Prevents duplicate deals using unique deal ID constraints.

### 💾 Persistence
- Persists validated deals in a PostgreSQL database using Spring Data JPA.


### 📃 Logging
- Informative logging via SLF4J for key events and errors.
- Helps audit and trace FX deal submissions.

### 🧪 Testing
- JUnit 5 tests with Mockito cover service and validation logic.
- Ensures business rules are enforced correctly.

---

## ⚙️ Technologies Used

### Core Stack
- Java 21
- Spring Boot 3.4.2
- PostgreSQL 16
- Docker & Docker Compose

### Testing & Utilities
- JUnit 5
- Mockito
- AssertJ
- SLF4J

---

## 📦 Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/MohammedFytahi/Clustered-Data-Warehouse.git
```

### 2. Configure database (PostgreSQL)
Ensure PostgreSQL is running and update your `application.yml`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fx_deals
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build the project
```bash
mvn clean install
```

### 4. Run the application
```bash
mvn spring-boot:run
```

The service will start at `http://localhost:8080`.

---

## 🧪 Running Tests
```bash
mvn test
```

Unit tests are available in `ssrc/test/java/com/progressoft/clustereddatawarehouse/service/implementation`.

---

## 📬 API Endpoint

### `POST /api/v1/deals`
Submit a new FX deal.

#### Request Body
```json
{
  "id": "FX10003",
  "fromCurrencyIsoCode": "azd2",
  "toCurrencyIsoCode": "EUR",
  "dealAmount": 1200.50,
  "dealTimestamp": "2025-11-12T12:00:00"
}

```

#### Responses

201 Created – Deal persisted

400 Bad Request – Validation or duplicate error

#### Dockerized Setup
1. Clone the project
 ```bash
git clone https://github.com/MohammedFytahi/Clustered-Data-Warehouse.git
cd clustereddatawarehouse
 ```
2. Environment Configuration

Create the .env file.

 ```bash
POSTGRES_DB=fx_deals
POSTGRES_USER=youruser
POSTGRES_PASSWORD=yourpassword
   ```

3. Run the stack

 ```bash

   docker compose up -d --build

   ```
---

## 🧱 Project Structure
```bash

---

├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/progressoft/fxcluster
│   │   │       ├── controller/      # REST controller
│   │   │       ├── dto/             # Deal DTOs
│   │   │       ├── entity/          # JPA entity
│   │   │       ├── exception/       # Custom exceptions
│   │   │       ├── mapper/          # DTO <-> Entity
│   │   │       ├── repository/      # JPA repository
│   │   │       ├── service/         # Business logic
│   │   │      
│   │   └── resources
│   │       └── application.yml
│   └── test
│       └── service/                 # Unit tests
├── pom.xml

---