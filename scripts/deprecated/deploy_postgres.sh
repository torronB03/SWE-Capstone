#!/bin/bash

# running instructions are in the README file

# variables (container name, port, password, database name)
DB_CONTAINER_NAME="postgres_database_container"     # placeholder name
DB_PORT=5432                                        # using the default PostgreSQL port
DB_USERNAME="postgres"
DB_PASSWORD="postgres"                              # password that Scarlett mentioned (up for discussion)
DB_NAME="resources_db"                              # name of the initial database that is created inside docker

# Detect the operating system and handle path accordingly
OS_TYPE=$(uname -s)

if [[ "$OS_TYPE" == "Darwin" ]]; then
    # macOS: Use standard Unix paths
    USER_HOME="/Users/$(whoami)"  # macOS path format
    HOST_DB_FOLDER="$USER_HOME/your_project_folder"   # Change this to your folder path
    DESTINATION_PATH="/data"  # Destination inside the container
elif [[ "$OS_TYPE" == "Linux" || "$OS_TYPE" == "CYGWIN"* || "$OS_TYPE" == "MINGW"* ]]; then
    # Windows (Git Bash): Use Docker Desktop's path mapping for Windows files
    USER_HOME="/c/Users/$(whoami)"
    HOST_DB_FOLDER="/run/desktop/mnt/host/c/Users/$(whoami)/OneDrive/Documents/GitHub/Project-COSC-A451-2025-01-TR-Sw-Egrg/SQL_Scripts/data"   # Adjust path to your data folder
    DESTINATION_PATH="/data"  # Destination inside the container
else
    echo "Unsupported OS"
    exit 1
fi

# setting up a volume so that data persists inside the container
DB_VOLUME_NAME="postgres_data"

# checking if docker is open and running in the background
# will continue if it is, error and exit if it isn't
if ! docker info > /dev/null 2>&1; then
    echo "ERROR: Please start Docker and try again"
    exit 1
fi

# looks for any running postgreSQL container, then stops and deletes it
docker stop $DB_CONTAINER_NAME 2>/dev/null
docker rm -f $DB_CONTAINER_NAME 2>/dev/null

# deploy the container (default 'postgres' superuser)
echo "Deploying PostgreSQL container..."

# running the container with the pre-set information
docker run --name $DB_CONTAINER_NAME \
    -e POSTGRES_PASSWORD=$DB_PASSWORD \
    -e POSTGRES_DB=$DB_NAME \
    -p $DB_PORT:5432 \
    -v "$HOST_DB_FOLDER:$DESTINATION_PATH" \
    -d postgres:latest

# waiting for the container to initialize 
sleep 10

# Test PostgreSQL connection using the default `postgres` user
echo "Testing PostgreSQL connection..."

# run a query inside the container to check the connection
# docker exec -it $DB_CONTAINER_NAME psql -U postgres -d $DB_NAME -c "SELECT 1;"

# completion message
echo "PostgreSQL deployment completed and connection tested!"
# reveals the details needed to connect
echo "Deploy using the username: $DB_USERNAME"
echo "Password: $DB_PASSWORD"
echo "Port: $DB_PORT"
echo "Database Name: $DB_NAME"
