FROM maven:3.6.0-jdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn verify

FROM openjdk:11-jre-slim AS web
COPY --from=build /app/web/target/*.jar web.jar
EXPOSE 8080
ENTRYPOINT java -jar web.jar

FROM openjdk:11-jre-slim AS api
COPY --from=build /app/api/target/*.jar api.jar
EXPOSE 8080
ENTRYPOINT java -jar api.jar
