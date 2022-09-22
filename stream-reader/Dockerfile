FROM openjdk:11
WORKDIR /app
COPY target/stream-reader-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]