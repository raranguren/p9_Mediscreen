FROM maven:3.6.0-jdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn verify

FROM openjdk:11-jre-slim AS web
COPY --from=build /app/web/target/*.jar web.jar
ENTRYPOINT java -jar web.jar --spring.profiles.active=prod

FROM openjdk:11-jre-slim AS api
COPY --from=build /app/api/target/*.jar api.jar
ENTRYPOINT java -jar api.jar --spring.profiles.active=prod
