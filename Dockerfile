FROM openjdk:18-jdk-slim

WORKDIR /app

COPY build/libs/item-services-0.0.1-SNAPSHOT.jar app.jar

COPY .env /app/.env

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
