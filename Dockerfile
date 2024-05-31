FROM tomcat:9.0.55-jdk17

COPY target/MW.war /usr/local/tomcat/webapps/MW.war

EXPOSE 8080

CMD ["java", "-jar", "MW.war"] 


