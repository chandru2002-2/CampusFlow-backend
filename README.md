🚀 CampusFlow Backend

A Spring Boot backend application for managing campus operations with JWT Authentication, Role-Based Authorization, and Full CRUD APIs.

---

🔥 Features

🔐 JWT Authentication (Login / Register)
🛡️ Role-Based Authorization (ADMIN / USER)
👥 User Management (CRUD APIs)
📄 DTO Layer (Clean API responses)
🔍 Pagination & Search
🗄️ MySQL Database Integration
⚡ REST APIs tested via Postman / CLI

---

🛠️ Tech Stack

1.Java 17
2.Spring Boot 3
3.Spring Security
4.Spring Data JPA
5.MySQL
6.JWT (io.jsonwebtoken)
7.Maven

---

📂 Project Structure

```
com.example.CampusFlow
│
├── controller     → REST APIs
├── service        → Business logic
├── repository     → Database access
├── model          → Entity classes
├── dto            → Request/Response objects
├── security       → JWT + Filters
└── config         → Security configuration
```

---

🔐 Authentication Flow

1. User registers → password is hashed using BCrypt
2. User logs in → JWT token is generated
3. Token is sent in headers:

   ```
   Authorization: Bearer <TOKEN>
   ```
4. Protected APIs validate token using JWT filter

---

📌 API Endpoints

 🔹 Auth APIs

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| POST   | /auth/register | Register user     |
| POST   | /auth/login    | Login & get token |

---

🔹 User APIs

| Method | Endpoint    | Access       |
| ------ | ----------- | ------------ |
| GET    | /users      | USER / ADMIN |
| GET    | /users/{id} | USER / ADMIN |
| PUT    | /users/{id} | USER / ADMIN |
| DELETE | /users/{id} | ADMIN only   |

---

🧪 Sample CURL Commands

Register

```
curl -X POST http://localhost:8080/auth/register ^
-H "Content-Type: application/json" ^
-d "{\"name\":\"Chandru\",\"email\":\"chandru@gmail.com\",\"password\":\"1234\",\"role\":\"USER\"}"
```

Login

```
curl -X POST http://localhost:8080/auth/login ^
-H "Content-Type: application/json" ^
-d "{\"email\":\"chandru@gmail.com\",\"password\":\"1234\"}"
```

---

🗄️ Database Schema

```
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER'
);
```

---

🚀 How to Run

1. Clone the repository

```
git clone https://github.com/chandru2002-2/CampusFlow-backend.git
```

2. Configure MySQL in `application.properties`

3. Run the project

```
mvn spring-boot:run
```

4. Test APIs using Postman or CMD
   

📈 Future Enhancements

📚 Course Management Module
📅 Attendance System
💰 Fee Management
🖥️ CLI-based Menu System
🌐 Frontend Integration



👨‍💻 Author

Chandru M
| MCA Student | Backend Developer



⭐ If you like this project

Give it a ⭐ on GitHub and support!


