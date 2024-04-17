FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

COPY --from=build /target/DogsManagementSystem-0.0.1-SNAPSHOT.jar DogsManagementSystem.jar
CMD ["java", "-jar", "DogsManagementSystem.jar"]
