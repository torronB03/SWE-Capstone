#!/bin/bash

# chmod +x deploy.sh

# Define usage
usage() {
    echo "Usage: $0 [build|compose|all]"
    exit 1
}

# Set working directories
REPO_ROOT=$(git rev-parse --show-toplevel)
if [ $? -ne 0 ]; then
    echo "Not in a Git repository - aborting"
    exit 5
fi

SETTINGS="${REPO_ROOT}/scripts/settings.sh"
if [ ! -s "$SETTINGS" ]; then
    echo "Settings file not found or empty - aborting"
    exit 6
fi

source "$SETTINGS"

# Move to project root
pushd "$REPO_ROOT" > /dev/null

build_war() {
    echo "===== [BUILD] Cleaning and Compiling ====="
    rm -rf build/*
    find "$WARFOLDER" build -name "*.java" -exec rm -f '{}' \;

    mkdir -p build 

    echo "Compiling Java source files..."
     if [[ -f "java_env.sh" ]]; then
        source "java_env.sh"
        "$JAVA_HOME/bin/javac" $(find "src" -name "*.java") -d build -classpath "${JAVAC_CLASSPATH}"     
        echo "Building using forced version"
    else
        javac $(find "src" -name "*.java") -d build -classpath "${JAVAC_CLASSPATH}"    
    fi
    if [ $? -ne 0 ]; then
        echo "Compilation failed"
        exit 2
    fi

    echo "===== [BUILD] Preparing WAR folder ====="
    rm -rf "$WARFOLDER"
    $USE_WAR_MODEL && cp -R "$WAR_MODEL_FOLDER" "$WARFOLDER"

    cp -R build/* "$WARFOLDER/WEB-INF/classes"
    cp -R lib/* "$WARFOLDER/WEB-INF/lib"
    cp -R app/styles* "$WARFOLDER"
    cp -R app/assets* "$WARFOLDER"
    cp -R app/partials* "$WARFOLDER"
    find "$WEBAPP_FOLDER" -name "*.html" -exec cp {} "$WARFOLDER/pages" \;
    find "$WEBAPP_FOLDER" -name "*.js" -exec cp {} "$WARFOLDER/js" \;

    find "$WARFOLDER" -name "*.java" -exec rm -f '{}' \;

    echo "Creating WAR file '${WARNAME}.war'"
    (cd "$WARFOLDER" && jar cvf "../${WARNAME}.war" *)

    if [ "$DOCKFILE_HOME" != "$REPO_ROOT" ]; then
        echo "Copying WAR to Docker context: $DOCKFILE_HOME"
        cp -v "${WARNAME}.war" "$DOCKFILE_HOME"
    fi

    echo "WAR file built successfully."
}

build_compose()
{
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

    echo "Tomcat container running. Access your app at:"
    echo "→ http://localhost:8080/${WARNAME}/pages/home.html"

}



# Determine operation
case "$1" in
    build) build_war ;;
    # deploy) deploy_docker ;;
    compose) build_compose ;;
    all) build_war && build_compose ;;
    *) usage ;;
esac

popd > /dev/null
