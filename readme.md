## Smart Home - Switch 2023/2024 - Group 1

This repository contains a well-structured domain model for a smart home system, built using a Domain-Driven Design (
DDD) approach and Onion Architecture.

The core focus is on separating the business logic from infrastructure concerns like persistence, allowing for clean,
testable, and maintainable code.

### Features:

* **DDD Principles:** The codebase strictly adheres to DDD concepts, utilizing:
    * **Aggregate Roots:** Entities like `House`, `Room`, `Device`, `SensorType`, etc., act as entry points to manage
      related objects and ensure data consistency.
    * **Value Objects:** Immutable objects like `Address`, `GPS`, `DeviceName`, etc., represent specific data clusters
      without inherent identity.
    * **Repositories:** Interfaces define data access contracts for Aggregate Roots, decoupling domain logic from
      persistence.
    * **Factories:** Classes encapsulate object creation logic, providing a central place to manage object lifecycles.
    * **Assemblers:** Bridge the gap between domain objects and DTOs, facilitating data transfer without exposing domain
      details.
    * **Visitors:** Design pattern for type-specific operations on actuators and sensors, avoiding messy `instanceof`
      checks.

* **Rich Domain Entities:**  Models capture various aspects of a smart home:
    * **House:** Represents the overall smart home location with an address and GPS coordinates.
    * **Room:** Defines areas within the house with properties like name, dimensions, and floor.
    * **Device:**  Represents physical devices (e.g., power meters, thermostats) associated with rooms, with type, name,
      and status.
    * **SensorType & ActuatorType:** Define categories for sensors and actuators, specifying units and descriptions.
    * **SensorModel & ActuatorModel:** Specific implementations of sensors and actuators, linked to their respective
      types.
    * **Sensor & Actuator:** Concrete instances of models, attached to devices and providing reading values.
    * **Log:**  Records sensor readings with timestamps, enabling data analysis and reporting.

* **Flexible Implementation:** While the domain model is technology-agnostic, the repository interfaces allow for
  various persistence options.
    * Current implementations include:
        * In-memory repositories for testing and rapid prototyping.
        * JPA-based repositories for persistent storage using relational databases.
        * Spring Data JPA repositories providing convenient data access abstractions.

* **Clear Data Structures:**  The use of Value Objects ensures data integrity and clarity.
    * For example, an `Address` object enforces valid formats and prevents storing incomplete or inconsistent address
      data.

* **Robust Validation:**  Input validation is consistently applied throughout the domain model, preventing invalid data
  from corrupting the system's state.

* **DTO Mapping:**  Assemblers handle the conversion between domain objects and data transfer objects (DTOs), isolating
  the domain model from external concerns.

### Requirements:

* **Java:** JDK 17 or later.
* **npm:** v6 or later
* **Maven:** v3.6 or later
* **Docker:** for docker deployment.

### Getting Started:

1. **Clone the repository:**
   ```
   git clone https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1 smarthome
   ```

2. **Build the project:**
   ```bash
   cd smarthome
   ./build.sh
   ```

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Explore the codebase:**
    * Start with the `domain` package to understand the core entities and their relationships.
    * Review the `repository` package to see how data access is abstracted.
    * Look into the `persistence` package to explore the specific persistence implementations.

### Docker deployment:

1. **Clone the repository:**
   ```
   git clone https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1 smarthome
   ```
2. **Run the bash script:**
   ```bash
   cd smarthome
   ./build.sh docker
   ```
    * This will deploy 4 container, adminer for easier database management, tomcat to host the application backend,
      nginx to host the frontend, mariadb for the persistence.
    * Further information regarding the docker deployment can be found in the wiki of this project.

3. **Explore the application**
    * A live demo of the application is running on the DEI servers
    * You will need to connect to the DEI VPN in order to acess the demo.
    * For more information regarding deployment to a web server visit our wiki.
    * Check our application: [Live demo](http://vs460.dei.isep.ipp.pt/)
    * Deployment of recent versions is done using: [Jenkins](http://vs460.dei.isep.ipp.pt/jenkins)

### Project Structure:

* **`smarthome.domain`:** Contains the core domain logic, including entities, value objects, factories, and visitors.
* **`smarthome.repository`:** Defines repository interfaces for data access.
* **`smarthome.persistence`:** Implements repositories using different persistence mechanisms.
* **`smarthome.mapper`:** Provides assemblers for DTO mapping.
* **`smarthome.utils`:** Contains utility classes for validation, data loading, and more.
* **`smarthome.service`:** Contains the service layer classes.
* **`smarthome.controller`:** Contains the controllers for both CLI and REST API.

### Future Enhancements:

* **Event Sourcing:**  Introduce an event-driven architecture to capture the history of changes, enabling audit trails
  and more complex business logic.