FROM openjdk:17-jdk

COPY target/Online-Advertisement-0.0.1-SNAPSHOT.jar onlineadvertising.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/onlineadvertising.jar"]
