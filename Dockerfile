FROM gradle:8.7.0-jdk17-alpine AS build
WORKDIR /test-api/

COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN gradle bootJar --no-daemon

FROM openjdk:21

WORKDIR /test-api/app/

COPY --from=build /test-api/build/libs/test-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT java -jar app.jar