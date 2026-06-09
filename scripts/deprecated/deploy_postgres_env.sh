#!/bin/bash

# running instructions are in the README file

# get repository root for consistent directory
REPO_ROOT=$(git rev-parse --show-toplevel) 

# variables (container name, port, password, database name)
DB_CONTAINER_NAME="postgres_database_container"     # placeholder name
DB_PORT=5432                                        # using the default PostgreSQL port
DB_NAME="postgres"                              # name of the initial database that is created inside docker
DB_PERSISTENT="${REPO_ROOT}/sql/persistent"

# these should be grabbed from our .env file. Thank you

# Detect the operating system and handle path accordingly
OS_TYPE=$(uname -s)

# pull some definitions from settings, and from env file
source "${REPO_ROOT}/scripts/settings.sh"
source "${REPO_ROOT}/.env"

pushd "${REPO_ROOT}"

# checking if docker is open and running in the background
# will continue if it is, error and exit if it isn't
if ! docker info > /dev/null 2>&1; then
    echo "ERROR: Please start Docker and try again"
    exit 1
fi

# looks for any running postgreSQL container, then stops and deletes it
docker stop $DB_CONTNR 2>/dev/null
docker rm -f $DB_CONTNR 2>/dev/null

# deploy the container (default 'postgres' superuser)
echo "Deploying PostgreSQL container..."

docker build -f "${DB_DOCKER_FILE}" -t "${DB_IMAGE}" .

# running the container:
# DB_PASS pulled from .env
# DB_USER pulled from .env
# DB_PORT set in script
# DB_PERSISTENT holds any persistent data we may store, then is loaded into our container
# DB_IMAGE is the image built from DB_DOCKER_FILE (dbDockerfile)

docker run --name $DB_CONTNR \
    -e POSTGRES_PASSWORD=$DB_PASS \
    -e POSTGRES_DB=$DB_USER \
    -p $DB_PORT:5432 \
    -v "${DB_PERSISTENT}:/var/lib/postgresql/data" \
    -d $DB_IMAGE

# waiting for the container to initialize 
sleep 10

# Test PostgreSQL connection using the default `postgres` user
echo "Testing PostgreSQL connection..."

# run a query inside the container to check the connection
# check for windows to use winpty
if [[ "$OS_TYPE" == "MINGW"* ]]; then
    winpty docker exec -it $DB_CONTNR psql -U postgres -d $DB_NAME -c "SELECT 1;"
else
    docker exec -it $DB_CONTNR psql -U postgres -d $DB_NAME -c "SELECT 1;"
fi
# completion message
echo "PostgreSQL deployment completed and connection tested!"
# reveals the details needed to connect
echo "Deployed using the username: $DB_USER"
echo "Password: $DB_PASS"
echo "Port: $DB_PORT"
echo "Database Name: $DB_NAME"

popd