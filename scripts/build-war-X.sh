#!/bin/bash

chmod +x build-war-X.sh

# In order to ensure we use the shared settings (variables), we need to work with
# the top folder of the repository
REPO_ROOT=$(git rev-parse --show-toplevel)   #sets top level of repo

if [ $? -ne 0 ] # test the exit status $? for error (non-zero)
then
    echo "Not in a git repository - aborting"
    exit 5
elif [ ! -s "${REPO_ROOT}/scripts/settings.sh" ]
# tests that the settings file exists and that it is not empty
then
	echo "Settings file not available - are you running this in a different repo?"
	exit 6
fi
#

# To load variables into the current shell, you can't just run the settings script
# if you do that, it will run in a child process and this shell won't get the settings 
# at all
# use the `source` command to run the script inside the current shell
source "${REPO_ROOT}/scripts/settings.sh"

echo "Rebuilding the WAR file"

# There is some possibility that the script is being run from another folder. To allow
# simple file and folder references without a lot of variables and such, change directory 
# to the top folder of the repository
echo "Moving to top of Git repository"
pushd "${REPO_ROOT}"
# `pushd` changes directory after putting the current directory on the "directory stack"

echo Cleaning build folder
rm -rf build/*

echo Building Java classes
set -x
# If any Java source files got into the WAR model folder, purge them
find "${WARFOLDER}" build -name "*.java" -exec rm -f '{}' \;                                         
# compile every last Java source file and put all the classes into the `build` folder

if [[ -f "java_env.sh" ]]; then
  source "java_env.sh"
  "$JAVA_HOME/bin/javac" $(find "src" -name "*.java") -d build -classpath "${JAVAC_CLASSPATH}" 
  echo "Building using forced version"    
else
  javac $(find "src" -name "*.java") -d build -classpath "${JAVAC_CLASSPATH}"     
fi

                          
set +x

# test the exit status $? again
if [ $? -ne 0 ]
then
  echo compile failed
  exit 2
fi

# Put the class files into the skeleton WAR directory
# Java class files must be under WEB-INF/classes                     
# set -x
echo Copying Java classes and HTML files into model WAR folder
rm -fr "${WARFOLDER}" 

if $USE_WAR_MODEL
then
	cp -R "${WAR_MODEL_FOLDER}" "${WARFOLDER}"
fi
# recursive (capital R) verbose (v) copy of everything under `build` to the correct
# location in the WAR folder
cp -R build/* "${WARFOLDER}/WEB-INF/classes"

# copy lib to folder...
cp -R lib/* "${WARFOLDER}/WEB-INF/lib"

# copy styles to the war's styles OUTSIDE OF WEB-INF
cp -R app/styles* "${WARFOLDER}"

# copy assets to the war's assets OUTSIDE OF WEB-INF
cp -R app/assets* "${WARFOLDER}"

# copy every page to the war model
find "${WEBAPP_FOLDER}" -name "*.html" -exec cp {} "${WARFOLDER}/pages" \;

# copy partials over (cause its a separate folder with separate paths)
cp -R app/partials* "$WARFOLDER"

# copy every script to the war model
find "${WEBAPP_FOLDER}" -name "*.js" -exec cp {} "${WARFOLDER}/js" \;

# cp -vR app/views* "${WARFOLDER}"

# And again, in case `build` caught some Java source files, purge them
find "${WARFOLDER}" -name "*.java" -exec rm -f '{}' \;

echo "Building web archive '${WARNAME}.war' from skeleton '${WARFOLDER}'"

# Go into the skeleton WAR file so that 'jar' command will use it as 
# the root of the WAR
# set +x
(
	pushd "${WARFOLDER}";           # use `pushd` again to enter the WAR deploy folder
	jar cvf ../"${WARNAME}.war" *   # jar everything there (asterisk) into WAR in parent folder
)
# The parentheses create a bash "sub-shell" that goes into the WAR folder, 
# builds the WAR, then exits, leaving this script in the main repo folder
# jar "c" - create
# jar "v" - verbose
# jar "f ../Voting.war" - name of the output file is ../Voting.war

# Docker sometimes uses a special 'context' folder for things that you want in the docker
# image. This command copies the new WAR file to that location correctly
if [ "${DOCKFILE_HOME}" != "${REPO_ROOT}" ]
then
  echo "Populating Docker context ${DOCKFILE_HOME}"
  cp -v "${WARNAME}.war" "${DOCKFILE_HOME}"
fi


# OK, we have our WAR file and put it in the right place, return to where the shell 
# was when it was launched
popd
# popd takes the last folder from the directory stack and changes (back) to that folder

echo Finished building new War file