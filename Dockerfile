# Start with a base image containing JVM
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="fauzi.knightmaster.achmad@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The applicaitons's jar file
ARG JAR_FILE=order-api/target/order-api-1.0-SNAPSHOT.jar


# Add the application's jar to the container
ADD ${JAR_FILE} order-api-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/order-api-1.0-SNAPSHOT.jar"]