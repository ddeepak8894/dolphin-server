# FROM adoptopenjdk/openjdk11:alpine-jre
# COPY target/hospital-backend.jar hospital-backend.jar
# ENTRYPOINT ["java","-jar","/hospital-backend.jar"]
FROM maven:3.6-jdk-12-alpine as build
WORKDIR /wrk
COPY pom.xml .
COPY src src
RUN mvn clean install

FROM openjdk:17-jdk-slim
COPY --from=build /wrk/target/dolphin-data-server-0.0.1-SNAPSHOT.jar dolphin-data-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dolphin-data-server-0.0.1-SNAPSHOT.jar"]


