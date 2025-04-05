# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app
COPY target/javatest-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Package the application

# Run the application
#CMD ["java", "-jar", "target/javatest-1.0-SNAPSHOT-jar-with-dependencies.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]