FROM maven:3-eclipse-temurin-21 AS builder

WORKDIR /app

COPY src src
COPY .mvn .mvn
COPY pom.xml .
COPY mvnw.cmd .
COPY mvnw .

RUN chmod a+x /app/mvnw
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:21-jdk-bullseye

WORKDIR /app_run

COPY --from=builder /app/target/batch4-0.0.1-SNAPSHOT.jar beers.jar

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar beers.jar