# SLIK - Core Banking & Credit Microservices Platform

An enterprise-grade, multi-module microservices architecture built on **Spring Boot 3.x** and **Spring Cloud (2023.0.x)**. This platform orchestrates frontend presentation, unified security routing, and core credit reporting operations (SLIK) through a highly decoupled service design.

---

## 🏗️ System Architecture & Workflow

## 🏗️ System Architecture & Workflow

When a client interacts with the application, traffic flows sequentially through the edge layer down to the business engine, while relying on shared utility contracts:

```text
                  [ Bank-view ] (Presentation Layer)
                        │
                        ▼ (HTTP / AJAX)
                   [ gateway ] (Dynamic Edge Router)
                        │
                        ▼
                  [ identifier ] (Auth & Token Validation)
                        │
                        ▼ (Authenticated Request Context)
                   [ bank-core ] (Backend Core System Engine)
```
### Shared Global Dependencies
* 📦 **`bank-dto`** ── Used by ➔ `Bank-view` & `bank-core` (Shared Serialization Contracts)
* 📦 **`Bank-common`** ── Used by ➔ `Bank-view` & `bank-core` (Shared System Utilities)

---

## 📦 Module Breakdown

### 💻 Frontend & Edge Layers

#### 🏢 `Bank-view` (Frontend Layer)
* **Tech Stack:** JSP, Java Controllers (Spring MVC), AJAX, jQuery.
* **Responsibility:** The client-facing presentation layer. Renders secure web interfaces via JSPs and leverages jQuery AJAX for non-blocking asynchronous data fetching. Its MVC controllers strictly manage view navigation and pass data onward without direct database coupling.

#### 🔀 `gateway` (API Gateway Router)
* **Tech Stack:** Spring Cloud Gateway, Resilience4j.
* **Responsibility:** The single entry-point reverse proxy for the entire infrastructure. It handles cross-cutting edge concerns like dynamic routing, path predicates, rate limiting, and failure isolation using circuit breakers.

#### 🔑 `identifier` (Security & Authentication)
* **Tech Stack:** Spring Security, OAuth2 / JWT.
* **Responsibility:** Dedicated Identity Provider (IdP). It manages authentication logic, processes login credentials, issues security tokens, and ensures valid request context before passing traffic to downstream core services.

### ⚙️ Backend Core & Shared Layers

#### 🧠 `bank-core` (Core Business Logic)
* **Tech Stack:** Spring Boot, Spring Data JPA / Hibernate.
* **Responsibility:** The centralized business engine. Contains all core calculations, data persistence layers, banking domain services, and integrations for parsing credit payloads. Completely isolated behind the gateway network.

#### 📄 `bank-dto` (Data Transfer Objects)
* **Responsibility:** Holds decoupled POJO data contracts shared across both the Frontend (`Bank-view`) and Backend (`bank-core`). Centralizing DTOs ensures payload compliance and prevents structural serialization mismatches during build-time compilation.

#### 🛠️ `Bank-common` (Utilities & Helpers)
* **Responsibility:** A cross-cutting utility library holding stateless helper components (e.g., date-time Formatters, String validators, custom encryption components) utilized globally across multiple layers.

---

## 🔧 Getting Started & Compilation

### Prerequisites
* **Java:** JDK 17 (or JetBrains Runtime 17+)
* **Build System:** Apache Maven 3.9+

### Build Process
Because modules carry internal dependencies (e.g., core systems require the shared DTO/Common jars), build the project sequentially from your root directory or ensure the utility modules are compiled into your local `.m2` repository first:

```bash
# 1. Clone the repository
git clone [https://github.com/Hendra2016/SLIK.git](https://github.com/Hendra2016/SLIK.git)
cd SLIK

# 2. Compile shared libraries first
cd bank-dto && mvn clean install
cd ../Bank-common && mvn clean install

# 3. Build the deployable runtimes
cd ../gateway && mvn clean package
cd ../bank-core && mvn clean package
cd ../bank-view && mvn clean package
cd ../identity-service && mvn clean package
