#!/bin/bash

# script detects the last edited java file in the current working directory
# then compiles and runs it

# giving permissions to make sure the script is executable
chmod +x run_java_auto.sh

# get the last edited .java file in the src directory
cd "src/"
java_file=$(ls -t *.java 2>/dev/null | head -n 1)

# if it cannot find one, then echo an error message and return
if [ -z "$java_file" ]; then
    echo "No Java file found!"
    exit 1
fi

# otherwise, compile and run the java file it found
# moves the built class to the build folder
echo "Compiling and running: $java_file"
javac "$java_file" -classpath "../lib/*;." && java "${java_file%.*}" -classpath "../lib/*;."
mv "${java_file%.*}.class" "../build/"