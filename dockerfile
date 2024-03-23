# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Build the application
RUN ./mvnw package -DskipTests

# Make port 8080 available to the world outside this container
EXPOSE 5001

# Run the application
CMD ["java", "-jar", "target/redirectLink-0.0.1-SNAPSHOT.jar"]
