FROM tomcat:8.0
FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} SpringBootMySQLDocker-0.0.1.jar
ENTRYPOINT ["java","-jar","SpringBootMySQLDocker-0.0.1.jar"]