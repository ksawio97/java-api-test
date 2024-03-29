FROM gradle:latest AS build
WORKDIR /test-api/

COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN gradle build --no-daemon

FROM openjdk:21

WORKDIR /test-api/app/

COPY --from=build /test-api/build/libs/test-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8002
ENTRYPOINT java -jar app.jar