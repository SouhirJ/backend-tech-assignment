# backend-tech-assignment
This document will walk you through what this application is, how to run it and how to execute the tests associated with 
# About the Application
This is a simple web application that exposes a REST API for managing listings for online advertising service. This application uses Maven as the build tool and the current LTS version of Java 17.
# Getting Started
Follow the instructions below to set up the project on your local machine for development and testing purposes.
# Prerequisites
Ensure you have the following software installed on your system before proceeding:

 Java Development Kit (JDK) 17 or later
 
 Maven 
 
 Docker
 
# Installation
1. Clone the repository:

   git clone https://github.com/SouhirJ/backend-tech-assignment.git

2. Navigate into the project directory:

    cd your-repository-name

3. Use Maven to build the project:

    mvn clean install

4. You can then run the Spring Boot application using:

    mvn spring-boot:run

  -> The application will be available at http://localhost:8080.

# Testing the application
To run the tests you will need Docker desktop installed and running. This allows us to test as close to production as possible on our development machines as well as a clean and reproducible testing environment each time.

# GitHub Actions
If you want to deploy to Docker Hub a new version of your application each time a commit is made or merged into the master branch you can use GitHub actions.

# Contact
[Souhir Jedidi] - [souhirdjedidi@gmail.com]
