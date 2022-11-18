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

FROM mysql AS mysql-with-tables
ENV MYSQL_DATABASE=mediscreen \
    MYSQL_ROOT_PASSWORD=rootpassword
ADD src-mysql/schema.sql /docker-entrypoint-initdb.d
