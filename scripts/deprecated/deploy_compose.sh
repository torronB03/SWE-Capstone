#!/bin/bash

echo attempting to deploy both containers...
# get repository root for consistent directory
REPO_ROOT=$(git rev-parse --show-toplevel) 

# pull some definitions from settings
source "${REPO_ROOT}/scripts/settings.sh"

pushd "${REPO_ROOT}"

# checking if docker is open and running in the background
# will continue if it is, error and exit if it isn't
if ! docker info > /dev/null 2>&1; then
    echo "ERROR: Please start Docker and try again"
    exit 1
fi

# looks for any running containers, stop them
docker stop $DB_CONTNR 2>/dev/null
docker rm -f $DB_CONTNR 2>/dev/null

docker stop $TCAT_CONTNR 2>/dev/null
docker rm -f $TCAT_CONTNR 2>/dev/null

# i will be kinda annoyed if this is all it takes to deploy both containers but if it works then i dont care
# -f: specific file
# --build: build the images
# -d: detached mode (else it would execute in our terminal perma)

if [ "$1" = "--clean" ]; then
  echo "Clean rebuild initialized..."
  docker compose -f docker_compose.yml -p community-resources up build --no-cache
fi

docker compose -f docker_compose.yml -p community-resources up --build -d

# ominous message
echo "Check Docker"

popd