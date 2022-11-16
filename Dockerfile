FROM maven:3.6.0-jdk-11-slim AS build
COPY . /app
WORKDIR /app
RUN mvn verify

FROM openjdk:11-jre-slim
COPY --from=build /app/target/*.jar web.jar
EXPOSE 8080
ENTRYPOINT java -jar web.jar