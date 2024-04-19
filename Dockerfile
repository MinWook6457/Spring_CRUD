# docker 안에 tomcat 9.0 및 jdk 17 설정
FROM openjdk:17-alpine

RUN apk update && apk add --no-cache curl tar bash

RUN curl -O https://downloads.apache.org/tomcat/tomcat-9/v9.0.88/bin/apache-tomcat-9.0.88.tar.gz && \
    tar -xzf apache-tomcat-9.0.88.tar.gz && \
    mv apache-tomcat-9.0.88 /usr/local/tomcat && \
    rm apache-tomcat-9.0.88.tar.gz


EXPOSE 8080

COPY /target/MW.war /usr/local/tomcat/webapps/ROOT.war

CMD ["java", "-jar", "/usr/local/tomcat/webapps/ROOT.war"]