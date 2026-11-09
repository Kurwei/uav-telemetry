# 🛸 UAV Telemetry & Log REST API
 
A **Java / Spring Boot** RESTful API service designed to ingest, log, and serve real-time sensor data (GPS, altitude, speed, battery) from autonomous UAVs (Unmanned Aerial Vehicles) to Ground Control Stations (GCS).
 
The project is fully containerized using **Docker** and **Docker Compose**.

---
 
## 🛠 Tech Stack & Architecture
 
- **Backend Framework:** Java 21, Spring Boot

- **Database:** PostgreSQL 15 (Spring Data JPA / Hibernate ORM)

- **Containerization:** Docker, Docker Compose (Multi-Stage Build)

- **Security:** Device-level API Key authentication (`X-API-KEY` header)

- **Build Tool:** Maven

---
 
## 📐 Data Model & Relationships
 
The system consists of two primary entity models linked via a **One-to-Many** relationship:
 
1. **`UAV`:** Represents registered drone hardware. Automatically generates a unique UUID API Key prior to database insertion via `@PrePersist`.

2. **`TelemetryLog`:** Stores real-time flight logs linked to a specific UAV via `uav_id` foreign key.
 
---
 
## 🚀 Quick Start (Docker Compose)

Prerequisites: **Docker Desktop** installed on your machine.

1. Clone the repository:
```bash
git clone https://github.com/Kurwei/uav-telemetry.git
cd uav-telemetry
```

Spin up the containers:
 
```bash
docker-compose up --build
```

docker-compose ensures the PostgreSQL instance passes its health check (pg_isready) before launching the Spring Boot service. The API will listen on http://localhost:8080.

## 🔌 API Endpoints

1. Register a New UAV

HTTP Method: POST
 
URL: /api/v1/uavs
 
Request Body:

```JSON
{
"name": "Teknofest-Drone-1",
"model": "Jetson Orin NX"
}
Response (200 OK):
```

```JSON
{

"id": 1,

"name": "Teknofest-Drone-1",

"model": "Jetson Orin NX",

"apiKey": "f47ac10b-58cc-4372-a567-0e02b2c3d479"

}
```

2. Ingest Telemetry Data

HTTP Method: POST
 
URL: /api/v1/telemetry

HTTP Header: X-API-KEY: <DEVICE_API_KEY>

Request Body:

```JSON
{

"latitude": 37.8715,

"longitude": 32.4981,

"altitude": 120.5,

"speed": 14.2,

"battery": 88

}
```

3. Fetch Historical Telemetry Logs
 
HTTP Method: GET
 
URL: /api/v1/telemetry/uav/{uavId}
 
Example: http://localhost:8080/api/v1/telemetry/uav/1
