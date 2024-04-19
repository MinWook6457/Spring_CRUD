# Base image for Spring Boot application
FROM openjdk:17

# Add the Spring Boot JAR file
ADD /target/MW.jar /MW.jar

# Expose port
EXPOSE 8080

# Command to run the Spring Boot JAR file
ENTRYPOINT ["java","-jar","/MW.jar"]
