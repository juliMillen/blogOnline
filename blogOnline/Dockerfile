FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -e -DskipTests package

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/*jar blogOnline.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","blogOnline.jar"]