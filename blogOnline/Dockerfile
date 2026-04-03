#Etapa 1: compilar

FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

WORKDIR /app/blogOnline
RUN mvn clean package -DskipTests

#Etapa 2: correr
FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/blogOnline-0.0.1.jar blogOnline.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","blogOnline.jar"]