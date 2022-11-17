FROM maven:3-amazoncorretto-19 AS build
COPY . /app
WORKDIR /app
RUN mvn verify

FROM amazoncorretto:19 AS web
COPY --from=build /app/web/target/*.jar web.jar
ENTRYPOINT java -jar web.jar --spring.profiles.active=prod

FROM amazoncorretto:19 AS api
COPY --from=build /app/api/target/*.jar api.jar
ENTRYPOINT java -jar api.jar --spring.profiles.active=prod
