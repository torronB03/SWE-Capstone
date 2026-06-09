# Project WAR name — used for naming .war file and Tomcat context path
: ${WARNAME:="community-resources-app"}

# Folder where WAR contents are staged before building .war (actual WAR structure goes here)
: ${WARFOLDER:="community-resources.war"}

# Folder with static template for WAR (HTML/CSS/JS/assets/etc)
: ${WAR_MODEL_FOLDER:="WAR-model"}

# Tomcat Dockerfile location
: ${TCAT_DOCKER_FILE:="tomcatDockerfile"}

# Docker image and container naming
: ${TCAT_CONTNR:="cr-app"}
: ${TCAT_IMAGE:="${TCAT_CONTNR}-image"}

# DB docker file
: ${DB_DOCKER_FILE:="dbDockerfile"}
: ${DB_CONTNR:="cr-db"}
: ${DB_IMAGE:="${DB_CONTNR}-image"}

# Docker build context (typically root of repo)
: ${DOCKFILE_HOME:="${REPO_ROOT}"}

# Your app directory (contains `app/pages`, `app/styles`, etc.)
: ${WEBAPP_FOLDER:="app"}

# Use WAR model as a base when building WAR
: ${USE_WAR_MODEL:=true}

# OK we have stuff that is different for Windows or Linux/MacOS. If we get the CLASSPATH wrong
# then our Java compilation will fail. This block sets it up correctly for the operating system
# `uname` returns a version marker that indicates the host operating system
if uname | grep -iq MINGW # it's windows
then 
  JAVAC_CLASSPATH="build;lib/*"
else # MacOS and Linux
  JAVAC_CLASSPATH="build:lib/*"
fi
