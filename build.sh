#!/bin/bash

# Define the directories
NPM_DIR="./frontend/"
MVN_DIR="."
DOCKER_COMPOSE_DIR="./docker"
MODE=${1:-development}

# Function to handle errors
handle_error() {
    echo "Error: $1"
    exit 1
}

# Check for directory existence
check_directory() {
    if [ ! -d "$1" ]; then
        handle_error "Directory $1 does not exist."
    fi
}

# Check required directories
check_directory "$NPM_DIR"
check_directory "$MVN_DIR"
check_directory "$DOCKER_COMPOSE_DIR"

# Run npm build
echo "Running npm build in ${NPM_DIR} with mode: $MODE..."
npm --prefix "$NPM_DIR" run build -- --mode $MODE --emptyOutDir || handle_error "npm build failed."

echo "npm build succeeded. Running mvnw package in ${MVN_DIR}..."

if [ -x "${MVN_DIR}/mvnw" ]; then
    case "$MODE" in
        "docker")
            echo "Running mvnw package in ${MVN_DIR} with docker profile..."
            "${MVN_DIR}/mvnw" -f "${MVN_DIR}/pom.xml" clean package -DskipTests -Dspring.profiles.active=docker || handle_error "mvnw package failed."

            # Move files for docker mode
            echo "Moving files for docker mode..."
            cp "${MVN_DIR}/target/switch2023project_g1-1.0-SNAPSHOT.war" "${DOCKER_COMPOSE_DIR}/web/smarthome.war" || handle_error "Copying application WAR file to docker folder failed."

            echo "Backing up current frontend build folder..."
            if [ -d "${DOCKER_COMPOSE_DIR}/frontend/dist" ]; then
                mv "${DOCKER_COMPOSE_DIR}/frontend/dist" "${DOCKER_COMPOSE_DIR}/frontend/dist_backup" || handle_error "Backup of frontend build folder failed."
            fi

            echo "Moving new frontend build folder..."
            mv "${NPM_DIR}/dist" "${DOCKER_COMPOSE_DIR}/frontend/" || handle_error "Moving frontend build folder for docker folder failed."

            # Cleanup trap on exit and failure
            trap 'if [ -d "${DOCKER_COMPOSE_DIR}/frontend/dist_backup" ]; then mv "${DOCKER_COMPOSE_DIR}/frontend/dist_backup" "${DOCKER_COMPOSE_DIR}/frontend/dist"; echo "Restored backup of frontend build folder."; fi' ERR
            trap 'if [ -d "${DOCKER_COMPOSE_DIR}/frontend/dist_backup" ]; then rm -rf "${DOCKER_COMPOSE_DIR}/frontend/dist_backup"; echo "Removed backup of frontend build folder."; fi' EXIT

            echo "Deploying docker-compose..."
            docker compose -f "${DOCKER_COMPOSE_DIR}/docker-compose.yml" up -d || handle_error "docker compose up failed."
            ;;
        *)
            echo "Running mvnw package in ${MVN_DIR} with default profile..."
            "${MVN_DIR}/mvnw" -f "${MVN_DIR}/pom.xml" clean package || handle_error "mvnw package failed."
            rm -rf "${MVN_DIR}/src/main/resources/static" || handle_error "Removing frontend build folder for default profile failed."
            mv "${NPM_DIR}dist/" "${MVN_DIR}/src/main/resources/static/" || handle_error "Moving frontend build folder for default profile failed."
            ;;
    esac
    echo "Succeeded in running mvnw package in ${MVN_DIR}.."
else
    handle_error "mvnw file does not exist or is not executable in ${MVN_DIR}."
fi