# 💸 E-Wallet API

A RESTful API for a simple and secure e-wallet system built using **Spring Boot** with **DDD + Hexagonal Architecture**.

Users can register, log in, and manage their wallet (top-up, transfer, view transaction history).  
Designed to be modular, scalable, and production-ready.
A RESTful API for a simple and secure e-wallet system built using **Spring Boot** with **DDD + Hexagonal Architecture**.

Users can register, log in, and manage their wallet (top-up, transfer, view transaction history).  
Designed to be modular, scalable, and production-ready.

---

## 📌 Features

- ✅ User registration & login
- ✅ JWT authentication with refresh token (secure with Redis + Cookie)
- ✅ Logout with token blacklist
- ✅ Get current user (`/me`)
- 🚧 Balance top-up and transfer (in progress)
- 🚧 Transaction history (in progress)
- ✅ User registration & login
- ✅ JWT authentication with refresh token (secure with Redis + Cookie)
- ✅ Logout with token blacklist
- ✅ Get current user (`/me`)
- 🚧 Balance top-up and transfer (in progress)
- 🚧 Transaction history (in progress)

---

## 🧱 Architecture

- ✅ Domain-Driven Design (DDD)
- ✅ Hexagonal (Ports & Adapters) Architecture
- ✅ Multi-layered (domain, application, infrastructure, web)
- ✅ Separation of concern & testable design

---

## ⚙️ Tech Stack

| Layer      | Tools & Libraries           |
| ---------- | --------------------------- |
| Backend    | Java 21, Spring Boot        |
| Database   | PostgreSQL, Redis           |
| Security   | Spring Security, JWT        |
| Validation | Hibernate Validator         |
| Testing    | JUnit, Mockito (planned)    |
| Docs       | Swagger / OpenAPI (planned) |
| Build Tool | Maven                       |

## 🧱 Architecture

- ✅ Domain-Driven Design (DDD)
- ✅ Hexagonal (Ports & Adapters) Architecture
- ✅ Multi-layered (domain, application, infrastructure, web)
- ✅ Separation of concern & testable design

---

## ⚙️ Tech Stack

| Layer      | Tools & Libraries           |
| ---------- | --------------------------- |
| Backend    | Java 21, Spring Boot        |
| Database   | PostgreSQL, Redis           |
| Security   | Spring Security, JWT        |
| Validation | Hibernate Validator         |
| Testing    | JUnit, Mockito (planned)    |
| Docs       | Swagger / OpenAPI (planned) |
| Build Tool | Maven                       |

---

## 🚀 Getting Started

### ✅ Prerequisites

### ✅ Prerequisites

- Java 21
- Maven
- PostgreSQL
- Redis (for refresh token blacklist)
- Redis (for refresh token blacklist)

---

---

### 📦 Setup

<pre>
git clone https://github.com/fauzinashrullah/spring-ewallet
cd spring-ewallet
</pre>

---

### ⚙️ Configuration

Create your `application.properties` or `application.yml`:

<pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/ewallet
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.redis.host=localhost
spring.redis.port=6379

jwt.secret=your-very-secret-key
jwt.expiration=3600
</pre>

---

### ▶️ Run App

<pre>
./mvnw spring-boot:run
</pre>

App will be available at: `http://localhost:8080`

---

## 🧪 Sample Endpoints

| Endpoint                | Method | Description                |
| ----------------------- | ------ | -------------------------- |
| `/api/v1/auth/register` | POST   | Register new user          |
| `/api/v1/auth/login`    | POST   | Login and receive tokens   |
| `/api/v1/auth/logout`   | POST   | Logout and blacklist token |
| `/api/v1/auth/me`       | POST   | Get current user           |
| `/api/v1/auth/refresh`  | POST   | Refresh access token       |

---

## 🧑‍💻 Project Structure

```bash
src/main/java/com/example/ewallet/

├── auth/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── web/
│
├── user/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── web/
│
├── wallet/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── web/
│
├── transaction/
│   ├── domain/
│   ├── application/
│   ├── infrastructure/
│   └── web/
│
├── shared/
│   ├── config/
│   ├── exception/
│   ├── security/
│   └── base/
```

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE.md).

---

## 🙋‍♂️ About the Author

Created by **Fauzi Malik Nashrullah** —  
💼 A passionate backend developer specializing in **Java Spring Boot**, with clean code practices, modular architecture, and real-world problem solving.  
📫 Reach me on [LinkedIn](https://linkedin.com/in/fauzi-malik-nashrullah) or check more projects on [GitHub](https://github.com/fauzinashrullah)

---

## 🌟 Feedback & Support

If you found this project helpful, consider leaving a ⭐ on the repo — it helps others discover it too!  
Feel free to open issues, submit PRs, or drop suggestions for improvement. Collaboration is welcome!
