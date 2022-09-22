FROM openjdk:11
WORKDIR /app
COPY target/view-producer-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]