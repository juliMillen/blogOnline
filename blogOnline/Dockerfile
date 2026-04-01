FROM eclipse-temurin:21-jre

WORKDIR /app
COPY target/blogOnline-0.0.1.jar blogOnline.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","blogOnline.jar"]