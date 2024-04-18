# Base Image
FROM openjdk:17

ADD target/MW.jar /MW.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/MW.jar"]