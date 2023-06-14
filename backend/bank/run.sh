#!/bin/bash

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Installing Maven..."
    sudo apt update
    sudo apt install maven -y
fi

# Run the mvn clean package command
mvn clean package
cd target
java -jar bank-0.0.1-SNAPSHOT.jar
