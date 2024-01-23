# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Optionally set a maintainer or label
LABEL maintainer="hydsainathreddy@gmail.com"

# Copy the pre-built jar from your local system into the image
COPY JarFile/receiptprocessor.jar app.jar

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]
