FROM openjdk:17-alpine

WORKDIR /app

COPY target/MW.war MW.war

CMD ["java", "-jar", "MW.war"]
