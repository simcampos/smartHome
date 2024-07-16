## Deploying SmartHome Application with Docker

This guide will walk you through deploying your SmartHome application using Docker and Docker Compose. We will cover the following steps:

**1. Prerequisites**

* **Docker:** Ensure you have Docker installed on your system. If not, download and install it from the official [Docker website](https://www.docker.com/).
* **Docker Compose:** Docker Compose is essential for managing multi-container applications. Install it according to the instructions on the official [Docker Compose website](https://docs.docker.com/compose/).
* **Git:**  We'll use Git to clone the project repository (assuming it's hosted on a platform like GitHub). Download and install Git from the official [Git website](https://git-scm.com/) if you don't have it.
* **Project Repository:** You need access to the project repository containing the Dockerfiles and source code.
* **Dependencies:** Ensure that the project dependencies are installed and configured correctly, check git repository for more information.

**2. Project Structure**

Verify that your project directory has the following structure:

```
smarthome/
├── db/
│   └── Dockerfile
├── web/
│   ├── Dockerfile
│   ├── context.xml
│   ├── smarthome.war
│   └── tomcat-users.xml
├── frontend/
│   ├── Dockerfile
│   ├── nginx.conf
│   └── dist/
└── docker-compose.yml
```

**3. Building and Running with Docker Compose**

* **Navigate to Project Directory:** Open your terminal or command prompt and navigate to the root directory of your SmartHome project (where the `docker-compose.yml` file is located).

* **Build the Images:** Build the Docker images for your database, web application, and frontend using the following command:

* **Ensure that the artifacts are built and placed in the respective directories:** You can use "build.sh docker" to automate the all process 

   ```bash
   docker-compose build
   ```

* **Run the Application:** Start all the services defined in your `docker-compose.yml` file:

   ```bash
   docker-compose up -d 
   ```

  The `-d` flag runs the containers in detached mode (in the background).

**4. Accessing Your Application**

* **Frontend:** Your frontend application should now be accessible in your web browser at `http://localhost:7070` (or the port you have mapped in your `docker-compose.yml`).
* **Adminer:**  You can manage your MariaDB database using Adminer at `http://localhost:8282`.
* **REST API:** Your Spring Boot backend API should be accessible at `http://localhost:8080/smarthome/`

**5. Stopping the Application**

To stop the Docker containers:

```bash
docker-compose down
```

**Explanation of Docker Components:**

* **`db/Dockerfile` (MariaDB):**
    - Uses the `mariadb:10.11.8` image.
    - Sets environment variables for the root user and database credentials.
    - Exposes port `3306` for database connections.

* **`web/Dockerfile` (Spring Boot):**
    - Uses the `tomcat:10.1-jdk17` image.
    - Copies the `tomcat-users.xml` and `context.xml` configuration files.
    - Exposes port `8080` for the Tomcat web server.
    - Copies your built Spring Boot WAR file (`smarthome.war`) into the Tomcat webapps directory.
    - Starts Tomcat.

* **`frontend/Dockerfile` (Nginx):**
    - Uses the `nginx:1.19.0-alpine` image.
    - Copies the built frontend files to the Nginx HTML directory.
    - Copies the `nginx.conf` file to configure Nginx as a reverse proxy.
    - Exposes port `80` for HTTP traffic.
    - Starts Nginx.

* **`docker-compose.yml`:**
    - Defines the services (db, adminer, web, frontend).
    - Builds images from their respective Dockerfiles.
    - Sets up port mappings.
    - Defines dependencies between services (e.g., the web service depends on the db service).

* **`nginx.conf`:**
    - Configures Nginx as a reverse proxy to forward requests to your Spring Boot backend (`/api/` path) and serve the frontend for all other requests.

**Additional Notes:**

* **Build using build.sh:** You can use the bash script `build.sh` with flag `docker` to build the frontend and backend applications. Move them into the respective directories and then automatically build the Docker images.
* **Adjust Ports:** Modify the port mappings in the `docker-compose.yml` file if needed to avoid conflicts with other applications running on your system.
* **Environment Variables:** Use environment variables in your `docker-compose.yml` file to configure settings like database credentials, API endpoints, etc. This makes your deployment more flexible.
* **Persistence:** For persistent data storage, consider using Docker volumes to store your database data outside the container's writable layer.
* **Docker Hub:** For easier sharing and deployment, consider pushing your built Docker images to a registry like Docker Hub.
