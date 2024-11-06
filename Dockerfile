# Use the official Maven image with slim to build the application
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copy the pom.xml and the source code
COPY pom.xml .
COPY src ./src

# Build the application, skipping tests for faster build
RUN mvn clean install -DskipTests

# Use the official OpenJDK Alpine image to run the application
FROM openjdk:17-alpine
WORKDIR /app

# Copy the jar file from the build stage
#COPY --from=build /app/target/*.jar app.jar
ADD userbankinfo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
