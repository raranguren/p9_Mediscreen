FROM maven:3-amazoncorretto-19 AS build
COPY . /app
WORKDIR /app
RUN mvn verify

FROM amazoncorretto:19 AS web
COPY --from=build /app/src-8080-web/target/*.jar web.jar
ENTRYPOINT java -jar web.jar --spring.profiles.active=prod

FROM amazoncorretto:19 AS api-patient
COPY --from=build /app/src-8081-api-patient/target/*.jar api.jar
ENTRYPOINT java -jar api.jar --spring.profiles.active=prod
