# Use a base image with Java pre-installed
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built from the Spring Boot project to the container
COPY target/*.jar /app/app.jar

# Set the profile to 'docker'
ENV SPRING_PROFILES_ACTIVE=docker

# Expose the port your Spring Boot application uses
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]