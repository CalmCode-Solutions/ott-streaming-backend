# OTT Streaming Service - Backend API

A robust RESTful API built with Spring Boot for an OTT (Over-The-Top) streaming platform. This backend powers user authentication, video content management, live channels, user libraries (favorites, watch history, progress tracking), and subscription plans.

## 🚀 Tech Stack

* **Framework:** Java 17 / Spring Boot 3.x
* **Security:** Spring Security with JWT (JSON Web Tokens)
* **Database:** PostgreSQL (Hosted on [Neon Serverless DB](https://neon.tech/))
* **Migrations:** Flyway
* **ORM:** Spring Data JPA / Hibernate
* **API Documentation:** Swagger UI / OpenAPI 3
* **Tools:** Lombok, Maven

## 📦 Features

* **Authentication:** User registration, login, and JWT token refresh.
* **Role-Based Access Control:** `ADMIN` routes for content management, `USER` routes for viewing and interactions.
* **Content Delivery:** APIs for retrieving Movies, Series, Documentaries, and Live TV Channels.
* **User Library:** Manage favorite videos, watch history, and "continue watching" progress.
* **Subscriptions:** View available plans, subscribe, and check active subscription status.
* **Admin Portal:** Statistics, user management, and video CRUD operations.

## 🛠️ Prerequisites

* [JDK 17](https://adoptium.net/) or higher
* [Maven](https://maven.apache.org/) 3.6+
* A [Neon DB](https://neon.tech/) account (for PostgreSQL)
* IDE (IntelliJ IDEA, Eclipse, or VS Code)

## ⚙️ Environment Variables

Before running the application, you need to configure the following environment variables (or set them in your IDE's Run Configuration) to connect to your Neon PostgreSQL database securely:

```env
DB_URL=jdbc:postgresql://<your-neon-hostname>.aws.neon.tech/neondb?sslmode=require
DB_USERNAME=neondb_owner
DB_PASSWORD=your_super_secret_password

# Optional (Defaults are provided in application.yml)
JWT_SECRET=your_jwt_secret_key
CORS_ORIGINS=http://localhost:5173
PORT=8080
