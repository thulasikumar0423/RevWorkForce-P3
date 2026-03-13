# 🚀 RevWorkforce - Enterprise Microservices Platform (P3)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Angular](https://img.shields.io/badge/Angular-18.2-red)](https://angular.io/)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue)](https://www.docker.com/)
[![Jenkins](https://img.shields.io/badge/CI%2FCD-Jenkins-orange)](https://www.jenkins.io/)

RevWorkforce (P3) is a cloud-native, scalable Human Resource Management (HRM) system. It leverages a modern microservices architecture to automate workforce operations, leave tracking, and performance appraisals with high availability and fault tolerance.

---

## 📖 Table of Contents
*   [🌟 Overview](#-overview)
*   [🏛️ Architecture](#-architecture)
*   [🛠️ Technology Stack](#-technology-stack)
*   [✨ Key Features](#-key-features)
*   [🚀 Installation & Quick Start](#-installation--quick-start)
*   [🔍 Code Quality (SonarQube)](#-code-quality-sonarqube)
*   [🔄 CI/CD Pipeline (Jenkins)](#-cicd-pipeline-jenkins)
*   [🩹 Troubleshooting](#-troubleshooting)

---

## 🌟 Overview
RevWorkforce (P3) decomposes the monolithic HRM system into **9 specialized services**. This allows each module (like Leave or Performance) to scale independently, ensuring the platform remains responsive even under heavy corporate load.

## 🏛️ Architecture
The platform follows a **Distributed Microservices Pattern** managed by a centralized API Gateway and Discovery Server.

| Service Name | Port | Description |
| :--- | :--- | :--- |
| **API Gateway** | `8080` | Entry point, JWT Auth, and routing |
| **Eureka Server** | `8761` | Service discovery and registration |
| **Config Server** | `8888` | Centralized external configuration |
| **User Service** | `8081` | Authentication and user profiles |
| **Leave Service** | `8082` | Leave requests and balance tracking |
| **Performance Service** | `8083` | Employee goals and appraisals |
| **Employee Management** | `8084` | Departments and org structure |
| **Notification Service** | `8085` | Real-time system alerts |
| **Reporting Service** | `8086` | Analytics and dashboards |

---

## 🛠️ Technology Stack

### Backend
- **Core:** Spring Boot 3.2.2 / Spring Cloud 2023
- **Infrastructure:** Netflix Eureka (Discovery), Spring Cloud Gateway, Config Server
- **Security:** Spring Security + Stateless JWT
- **Communication:** OpenFeign & REST
- **Database:** MySQL 8.0

### Frontend
- **Framework:** Angular 18.2 (Standalone Components)
- **Styling:** Bootstrap 5.3 & Vanilla CSS

### DevOps
- **Containerization:** Docker & Docker Compose
- **Pipeline:** Jenkins (Groovy Scripted Pipeline)
- **Analysis:** SonarQube Community Edition

---

## ✨ Key Features

### 👤 Admin Features
- **Lifecycle Management:** Control departments, designations, and employee records.
- **Org Controls:** Global holiday configuration and company-wide announcements.
- **Analytics:** Comprehensive reporting on leave patterns and audit logs.

### 👔 Manager Features
- **Team Presence:** Real-time visibility into team leave schedules.
- **Performance:** Conduct reviews and set development goals for reports.

### 💼 Employee Features
- **Self-Service:** Submit leave applications and track goal progress.
- **Engagement:** Real-time notifications for approval status and updates.

---

## 🚀 Installation & Quick Start

### 1. Prerequisites
- **Java 17+**
- **Node.js 18+**
- **Docker Desktop** (8GB RAM / 64GB Disk recommended)

### 2. Clone the Repository
```bash
git clone https://github.com/thulasikumar0423/RevWorkForce-P3.git
cd RevWorkForce-P3
```

### 3. Configuration
The application pulls properties from the [Team Config Repository](https://github.com/RevWorkForceTeam/RevWorkForce-Config.git). Ensure your `mysql` and `jwt` secrets are updated in the `application.yml` via the Config Server.

### 4. Running with Docker (Best Way)
```bash
# Start all 10 services plus Infrastructure (Jenkins, Sonar, DB)
docker-compose -f devops/docker/docker-compose.yml up -d --build
```
*The UI will be available at: [http://localhost:4200](http://localhost:4200)*

---

## 🔍 Code Quality (SonarQube)
We use SonarQube to maintain strict security and clean code standards.

1.  **Start Sonar:** `docker start sonarqube` (Available at port `9000`)
2.  **Scan Backend:**
    ```bash
    mvn clean verify sonar:sonar -Dsonar.projectKey=revworkforce-backend
    ```
3.  **Scan Frontend:**
    ```bash
    cd frontend/revworkforce-ui
    npx sonar-scanner -Dsonar.projectKey=revworkforce-frontend
    ```

---

## 🔄 CI/CD Pipeline (Jenkins)
The integrated **Jenkinsfile** automates the entire lifecycle:
- **Build:** Parallel Maven builds (Backend) & Angular Prod build (Frontend).
- **Quality:** Automated Sonar scans with memory-optimized JS runners.
- **Containerize:** Docker images tagged as `thulasikumarp/revworkforce-*`.
- **Deploy:** Automatic push to Docker Hub.

---

## 🔑 Default Credentials
| System | URL | Username | Password |
| :--- | :--- | :--- | :--- |
| **Application** | `localhost:4200` | `admin@gmail.com` | `admin123` |
| **Jenkins** | `localhost:8088` | `thulasikumarp` | *(Your Setup Pass)* |
| **SonarQube** | `localhost:9000` | `admin` | `admin` |

---

## 🩹 Troubleshooting
- **`Killed` Error:** Node/Java ran out of RAM. Ensure Docker Desktop has **8GB RAM** allocated.
- **Port Conflict:** If a service fails to start, check if port `8080` (Gateway) or `4200` (UI) is already in use.
- **Docker Permission:** If Jenkins cannot build, run:
  `docker exec -u root jenkins chmod 666 /var/run/docker.sock`

---

**⭐ If you find this project useful, give it a star!**
Maintainer: [Thulasi Kumar Pachikayala](https://github.com/thulasikumar0423)
