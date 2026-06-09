#!/bin/bash
# This script is the master for building the Docker environment with one Oracle container,
# one Tomcat container.  It expects to find a Docker machine "cosca451" previously created

# Load the Settings file.  Contains various settings that could be shared among
# multiple scripts
RUNDIR=$(dirname "$0")  # finds the folder in which this `mkdock.sh` script lives
REPO_ROOT=$(git rev-parse --show-toplevel)   #sets top level of repo

if [ $? -ne 0 ]  # exit status from `git` will be nonzero if a problem
then
    echo "Not in a git repository - aborting"
    exit 5
elif [ ! -s "${REPO_ROOT}/scripts/settings.sh" ]   # don't run without the `settings.sh` file
then
	echo "Settings file not available - are you running this in a different repo?"
	exit 6
fi
#

# load the settings from settings.sh
# note you must use `source` in order to execute the script within this current shell,
# otherwise the settings will be made in a subshell that exits with no effect
source "${REPO_ROOT}/scripts/settings.sh"

cd "${REPO_ROOT}"

# Clear out unneeded Docker containers and images - save disk space
docker system prune -f

# Stop and remove existing Tomcat container if running
docker stop "$TCAT_CONTNR" 2> /dev/null
docker rm -f "$TCAT_CONTNR" 2> /dev/null

# Build new Docker image from Dockerfile
echo "Building Tomcat image: ${TCAT_IMAGE}"
docker build -t "${TCAT_IMAGE}" -f "${TCAT_DOCKER_FILE}" .

# Run the container with port mapping
echo "Running Tomcat container: ${TCAT_CONTNR}"
docker run -d --name "${TCAT_CONTNR}" -p 8080:8080 "${TCAT_IMAGE}"

echo "Tomcat container is running. Access your application at:"
echo "http://localhost:8080/${WARNAME}/pages/home.html"

