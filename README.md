# SLIK
SLIK banking login and add user Microservice
# SLIK - Microservices Architecture

A robust, enterprise-grade backend infrastructure utilizing a microservices architecture built on **Spring Boot 3.x** and **Spring Cloud (2023.0.x)**. This platform provides secure routing, resilient service-to-service communication, and unified orchestration for core banking/credit data exchange operations.

## 🚀 Key Architectural Components

* **API Gateway (`api-gateway`)**: The single entry point for all client requests. Built using **Spring Cloud Gateway** to handle dynamic routing, rate limiting, and request/response transformations.
* **Resilience & Fault Tolerance**: Fully integrated with **Resilience4j (Reactor)** to provide circuit breaker patterns, preventing cascading failures across downstream microservices.
* **Enterprise Security**: Supports secure SSL/TLS communication configurations, custom keystores/truststores, and OAuth2 validation at the edge.

---

## 🛠️ Tech Stack & Prerequisites

* **Java**: JDK 17 (or JetBrains Runtime 17+)
* **Framework**: Spring Boot 3.x / Spring Cloud 2023.0.3
* **Build Tool**: Apache Maven 3.9+
* **Core Dependencies**:
    * `spring-cloud-starter-gateway`
    * `spring-cloud-starter-circuitbreaker-reactor-resilience4j`

---

## 🔧 Getting Started & Installation

### 1. Clone the Repository
```bash
git clone [https://github.com/Hendra2016/SLIK.git](https://github.com/Hendra2016/SLIK.git)
cd SLIK
