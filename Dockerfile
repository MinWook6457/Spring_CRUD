# Base Image
FROM openjdk:17

ADD /target/MW.war /MW.war

EXPOSE 8080

ENTRYPOINT ["java","-jar","/MW.jar"]