# 🏗️ Construction Management System

A console-based Java application for managing construction projects, employees, and expenses.

This project was built to practice object-oriented programming, layered architecture, Maven structure, and JSON persistence using Jackson.

---

## 🚀 Features

- Create and manage construction projects
- Add employees to constructions
- Register and categorize expenses
- Update construction status
- Calculate total expenses
- Persist data using JSON files
- Structured with Maven

---

## 🏗️ Project Architecture

The project follows a layered architecture:


User
↓
Menu (application layer)
↓
Service (business logic)
↓
DAO (data access)
↓
JSON file (persistence)


### 📦 Packages

- `application` → User interface (Menu & Main)
- `service` → Business logic
- `dao` → Data persistence layer
- `model` → Domain entities
- `enums` → Fixed value types
- `util` → JSON utilities

---

## 🛠️ Technologies Used

- Java 21
- Maven
- Jackson (JSON serialization/deserialization)
- Git

---

## 💾 Data Persistence

Data is stored locally in:


database/constructions.json


The system uses Jackson's `ObjectMapper` to convert Java objects into JSON format and back.

---

## 🧠 Concepts Applied

- Object-Oriented Programming (OOP)
- Separation of Concerns
- Layered Architecture
- Data Access Object (DAO) pattern
- JSON serialization
- Maven project structure

---

## 📌 Future Improvements

- Replace JSON with a relational database
- Implement REST API using Spring Boot
- Add unit tests with JUnit
- Create financial reports
- Add authentication system

---

## 👨‍💻 Author

João Pedro Carvalho

Software Engineering Student  
Focused on Java backend development.
