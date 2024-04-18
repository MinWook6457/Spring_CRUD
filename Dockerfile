# Base image for Spring Boot application
FROM openjdk:17 AS spring-boot-app

# Add the Spring Boot JAR file
ADD /target/MW.jar /app/MW.jar

# Expose port
EXPOSE 8080

# Command to run the Spring Boot JAR file
ENTRYPOINT ["java","-jar","/app/MW.jar"]

# Base image for PostgreSQL database
FROM postgres:latest AS postgres-db

# Set environment variables for PostgreSQL
ENV POSTGRES_DB=bs
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root

# Expose the PostgreSQL port
EXPOSE 5432
