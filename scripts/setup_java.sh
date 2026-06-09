#!/bin/bash

# script that uses SDKMAN! to do install java 17 if it isn't found on your system
# writes the new path to a separate file, which build scripts will use
# THIS IS A ONE TIME USE. DO NOT USE THIS AGAIN

# giving permissions to make sure the script is executable
chmod +x setup_java.sh

REPO_ROOT=$(git rev-parse --show-toplevel) 
OS_TYPE=$(uname)

pushd ${REPO_ROOT} > /dev/null

if [[ "$OS_TYPE" == "Darwin" || "$OS_TYPE" == "Linux" ]]; then

    echo "OSX/Linux detected. Will use SDKMAN."
    
    if [ -z "$SDKMAN_DIR" ]; then
        # get dependencies for sdkman
        sudo apt update && sudo apt install -y curl zip unzip sed grep tar bash

        # source sdkman shell
        curl -s "https://get.sdkman.io" | bash
        source "$HOME/.sdkman/bin/sdkman-init.sh"
    fi

    # install java 17 if needed
    if ! sdk list java | grep -q "17"; then
        echo "Installing Java 17..."
        sdk install java 17.0.9-tem
        sdk default java 17.0.9-tem
    fi

    # Use "current" symlink so path is generic
    JAVA_PATH="$HOME/.sdkman/candidates/java/current"

    # new file
    OUTPUT_FILE="${REPO_ROOT}/java_env.sh"

    # write JAVA_HOME and PATH to output file
    {
        echo "export JAVA_HOME=\"$JAVA_PATH\""
    } > "$OUTPUT_FILE"

    exit 0
elif [[ "$OS_TYPE" == "CYGWIN"* || "$OS_TYPE" == "MINGW"* ]]; then
    JAVA_BASE="/c/Program Files/Java"
    JAVA_HOME_CANDIDATE=""

    # Loop through directories and find one with version 17
    for dir in "$JAVA_BASE"/*; do
        if [[ -x "$dir/bin/java.exe" ]]; then
            version=$("$dir/bin/java.exe" -version 2>&1 | awk -F '"' '/version/ {print $2}')
            if [[ "$version" == 17* ]]; then
                JAVA_HOME_CANDIDATE="$dir"
                break
            fi
        fi
    done

    OUTPUT_FILE="${REPO_ROOT}/java_env.sh"
    if [ -n "$JAVA_HOME_CANDIDATE" ]; then
        echo "Found Java 17 in $JAVA_HOME_CANDIDATE"
        {
            echo "export JAVA_HOME=\"$JAVA_HOME_CANDIDATE\""
        } > "$OUTPUT_FILE"
    else
        echo "Java 17 not found. Please install it manually."
        echo "https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html"
    fi
else
    echo "Unsupported OS: $OS_TYPE"
    exit 1
fi

popd > /dev/null