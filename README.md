# Media Tracker API

A professional, production-ready RESTful backend API designed to organize and monitor personal media backlogs (video games, movies, books, and shows). Built using **Java 25**, **Spring Boot 4**, and a fully isolated **Layered Architecture**, this project demonstrates core enterprise backend development competencies, automated Object-Relational Mapping (ORM), robust database transactional stability, and standardized API engineering.

This repository serves as a portfolio piece showcasing clean coding standards, robust decoupling practices, strict separation of concerns, and industry-standard testing workflows using Postman.

---

## 📋 Table of Contents
1. [Tech Stack](#-tech-stack)
2. [Data Model](#-data-model)
3. [API Endpoints](#-api-endpoints)
4. [Getting Started](#-getting-started)
5. [Configuration](#-configuration)
6. [Error Handling](#-error-handling)
7. [Suggested Setup Order](#-suggested-setup-order)
8. [Postman Testing Screenshots](#-postman-testing-screenshots)

---

## 💻 Tech Stack

* **Runtime Environment:** Java 25 (OpenJDK)
* **Framework Engine:** Spring Boot 4.0.6 (Spring Web, Spring Data JPA)
* **Database Integration:** H2 Database (In-Memory Configuration for atomic, stateless deployment tracking)
* **ORM Provider:** Hibernate (Automated DDL Schema Mapping generation)
* **Build Optimization Tool:** Apache Maven
* **API Verification Suite:** Postman (Collections packaged within root)

---

## 📊 Data Model

The core of the application relies on the `MediaItem` domain entity. Through Hibernate's Object-Relational Mapping (ORM), this Java blueprint is automatically translated into an internal relational SQL table layout named `media_items`.

| Java Field Name | SQL Column Type | Property Constraints / Description | Example Value |
| :--- | :--- | :--- | :--- |
| `id` | `BIGINT` | Primary Key (`@Id`), auto-incremented automatically. | `1` |
| `title` | `VARCHAR(255)` | The title string of the entry. Cannot be empty. | `"Chrono Trigger"` |
| `type` | `VARCHAR(255)` | Specifies the media category classification. | `"Game"` |
| `status` | `VARCHAR(255)` | Current pipeline progress value state. | `"BACKLOG"` |
| `rating` | `INT` | Personal valuation evaluation score (Scale 1-10). | `10` |
| `notes` | `VARCHAR(255)` | Dynamic text observations, logs, or reviews. | `"Classic SNES RPG."` |

---

## 📡 API Endpoints

The API conforms to strict REST architectural standards using standard HTTP status codes to communicate execution responses to client tools like Postman.

| HTTP Protocol | Resource Endpoint URL Path | Action Description | Target Return Payload Type | Expected Success Status |
| :--- | :--- | :--- | :--- | :--- |
| **`POST`** | `http://localhost:8080/api/media` | Persists a new item into the backlog tracker. | Single JSON Entity Object | **`201 Created`** |
| **`GET`** | `http://localhost:8080/api/media` | Queries table rows and fetches all backlog items. | Wrapped JSON Array `[ ]` | **`200 OK`** |
| **`GET`** | `http://localhost:8080/api/media/{id}` | Extracts a single data match matching a key ID. | Single JSON Entity Object | **`200 OK`** |
| **`PUT`** | `http://localhost:8080/api/media/{id}` | Overwrites state details for an item by its ID. | Updated JSON Entity Object | **`200 OK`** |
| **`DELETE`**| `http://localhost:8080/api/media/{id}` | Vaporizes a target data row out of the system. | Empty Blank Structural Body | **`204 No Content`** |

---

## 🚀 Getting Started

Skip the command-line interfaces! Here is the visual, traditional method to clone, open, and run this repository using **IntelliJ IDEA**.

### 1. Cloning the Project Visually
1. Copy the URL of this GitHub repository.
2. Open **IntelliJ IDEA**. On the welcome splash window screen, click the **Get from VCS** button in the top right corner.
3. In the window pane that pops open, ensure the dropdown says **Git**.
4. Paste the repository link into the **URL** text box field.
5. Choose a local directory folder on your hard drive where you want the project to live, then click the blue **Clone** button.
6. *If prompted to trust the project, click **Trust Project**.*

### 2. Starting up the Application Server
1. Wait for IntelliJ to finish importing your dependencies (watch the progress loading status bar indicator in the bottom-right corner).
2. On the left project tree list sidebar folder navigator view, expand: `src -> main -> java -> com.azaanmp.media_tracker`.
3. Double-click the **`MediaTrackerApplication.java`** file to open it in the editor window.
4. Locate the green **Play/Run triangle arrow icon** located directly to the left of line 7 (`public class MediaTrackerApplication`).
5. Click that green arrow button and select **Run 'MediaTrackerApplication.main()'**.
6. The runtime environment log dashboard drawer console will pop open at the bottom. The server is live and holding connections open when you see: `Tomcat started on port 8080 (http)`.

---

## 🔧 Configuration

All backend environment settings are localized cleanly inside the centralized properties configuration manifest.

* **Location Path:** `src/main/resources/application.properties`
* **Operational Directives Configured:**
    * `server.port=8080`: Dictates that local clients like Postman route payloads here.
    * `spring.datasource.url=jdbc:h2:mem:mediadb`: Spun up natively in RAM for speed.
    * `spring.h2.console.enabled=true`: Enables a local web management GUI dashboard.
    * `spring.jpa.hibernate.ddl-auto=update`: Instructs Hibernate to read Java entities and handle tables automatically.
    * `spring.sql.init.mode=never`: Completely isolates the database runtime, forcing it to skip scanning for error-prone raw SQL files like `schema.sql`.

---

## ⚠️ Error Handling

This application uses precise **HTTP Response Entities** inside the REST controller layer to safely prevent system breakdowns or silent logic fails when receiving incomplete inputs:

* **Data Integrity Failures:** If an execution attempts to write improper records, Spring interceptors step in to intercept execution degradation.
* **Missing ID Requests (`404 Not Found`):** When triggering requests for specific resources (like `GET /api/media/999` or `PUT /api/media/999`) that do not exist inside the H2 database index, the service layer wraps the validation checks using Java's functional `Optional` utilities, causing the controller to return a standard `404 Not Found` header to Postman rather than crashing the thread.
* **Stateless Transaction Safety:** Since the underlying schema uses database-level identity generators, malformed individual data fields can be rejected safely without breaking the structural sequencing rows of the active database table.

---

## ⛓️ Suggested Setup Order

If you are reconstructing this system design blueprint step-by-step from scratch inside your IDE, adhere to this layered dependency construction sequencing outline to prevent class reference errors:

1. **Configure Build Blueprints (`pom.xml`):** Add core web dependencies and JPA drivers first so the IDE indexes your class libraries.
2. **Establish Server Properties (`application.properties`):** Register ports, database memory connections, and automated generation configurations.
3. **Define Domain Entity Layer (`/model`):** Build the foundational object blueprint classes containing entity annotations and getters/setters.
4. **Establish Persistence Intermediary Interface (`/repository`):** Create the repository layer interface extending `JpaRepository` to enable CRUD functions.
5. **Inject Business Services Logic Layer (`/service`):** Compose structural method definitions handling transaction flows and operations.
6. **Expose External API Communication Endpoints (`/controller`):** Connect HTTP network annotations to route live payloads to client interfaces.

---

## 📸 Postman Testing Screenshots

### 1. POST - Create Media Item
* **Endpoint:** `POST http://localhost:8080/api/media`
* **Description:** Sends a new media entry as a JSON payload. The server returns the persisted object along with a database-generated unique ID and a `201 Created` status.
![1_POST_Endpoint](https://github.com/AzaanMavan0327/Media-Tracker/blob/e4f2ce9ed2f6dcfb6ae45b2bb8b57c853a5af7ee/screenshots/Output%201.png)

---

### 2. POST - Create Media Item (Validation Failure Exception)
* **Endpoint:** `POST http://localhost:8080/api/media`
* **Description:** Demonstrates the application's input verification firewall. Sending a payload missing a required title field or out-of-bound numerical ranges is intercepted cleanly, returning a `400 Bad Request` along with the precise validation failure notification text.
![2_Validation_Failure_Response_Screenshot](https://github.com/AzaanMavan0327/Media-Tracker/blob/65ef5dcbbd423fdcab0f64574750fd8e0549e114/screenshots/Output%206.png)

---

### 3. GET - Fetch All Media Items
* **Endpoint:** `GET http://localhost:8080/api/media`
* **Description:** Retrieves all media items currently stored in the internal H2 memory index. The response payload is wrapped in a JSON array `[ ]` with a `200 OK` status.

![3_GET_All_Items](https://github.com/AzaanMavan0327/Media-Tracker/blob/e4f2ce9ed2f6dcfb6ae45b2bb8b57c853a5af7ee/screenshots/Output%202.png)

---

### 4. GET - Fetch Media Item By ID
* **Endpoint:** `GET http://localhost:8080/api/media/{id}`
* **Description:** Performs an index search for a specific item using its primary key (`/1`). Returns a single JSON entity object with a `200 OK` status.

![4_GET_By_ID](https://github.com/AzaanMavan0327/Media-Tracker/blob/e4f2ce9ed2f6dcfb6ae45b2bb8b57c853a5af7ee/screenshots/Output%203.png)

---

### 5. PUT - Update Media Item
* **Endpoint:** `PUT http://localhost:8080/api/media/{id}`
* **Description:** Overwrites the data fields of an existing record to track active progress changes. Returns the fully updated object body with a `200 OK` status.

![5_PUT_Update_Item](https://github.com/AzaanMavan0327/Media-Tracker/blob/e4f2ce9ed2f6dcfb6ae45b2bb8b57c853a5af7ee/screenshots/Output%204.png)

---

### 6. DELETE - Remove Media Item
* **Endpoint:** `DELETE http://localhost:8080/api/media/{id}`
* **Description:** Purges the target data row permanently from the system memory index. Returns a completely blank response body paired with a standard `204 No Content` status.

![6_DELETE_Item](https://github.com/AzaanMavan0327/Media-Tracker/blob/e4f2ce9ed2f6dcfb6ae45b2bb8b57c853a5af7ee/screenshots/Output%205.png)

---

## 🧑‍💻 Developer Profile
* **Developer Name:** Azaan Mavandadipur
* **GitHub Profile:** [AzaanMavan0328](https://github.com/AzaanMavan0327)
