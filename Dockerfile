FROM eclipse-temurin:22-jdk-alpine

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENV JAVA_OPTS="-Dspring.profiles.active=docker"
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]