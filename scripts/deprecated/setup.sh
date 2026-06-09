#!/bin/bash

# script that assigns a keyboard shortcut (alt + b) to the script "run_auto script" in order
# to automatically compile and run the last edited java file in the current working directory

# giving permissions to make sure the script is executable
chmod +x setup.sh

# check if ~/.inputrc exists
if [ ! -f ~/.inputrc ]; then
    # if not, then create it
    touch ~/.inputrc
fi

# defining the shortcut used (alt + b)
# runs the current working directory path to the file manually (run_java_auto detects the last java file that was used and runs it)
shortcut="\"\eb\": \"bash -c 'cd \\\"$PWD\\\" && ./run_java_auto.sh'\n\""

# checking if the shortcut is already set
if ! grep -q "$shortcut" ~/.inputrc; then
    # if it's not, then the shortcut will be set up
    echo 'Adding keyboard shortcut to ~/.inputrc...'
    # echo '"\eb": "run_java\n"' >> ~/.inputrc
    echo "$shortcut" >> ~/.inputrc
    echo "Shortcut set! Press Alt + B to run your Java script."
else
    echo "Shortcut already exists!"
fi

# reload the inputrc file
bind -f ~/.inputrc
echo "Setup complete!"
