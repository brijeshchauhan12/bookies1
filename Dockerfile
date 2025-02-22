# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application jar into the container
COPY target/bookies1-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 7777

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
