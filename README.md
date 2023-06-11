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

    mvn clean install -DskipTests

4. You can run this application from your favorite IDE or by running the following command:
        
        ./mvnw spring-boot:run
        
     -> The application will be available at http://localhost:8080/api/v1/.
     
      The API is accessed from the URL
       
         http://localhost:8080/swagger-ui/index.html

5. Try it out with Docker
    
    You'll need Docker installed.
   
         docker-compose up 
    
  -> The application will be available at http://localhost:8088/api/v1/.
  
   The API is accessed from the URL
  
      http://localhost:8088/swagger-ui/index.html

# GitHub Actions
To deploy to Docker Hub a new version of our application each time a commit is made or merged into the master branch we can use GitHub actions.

# Contact
[Souhir Jedidi] - [souhirdjedidi@gmail.com]
