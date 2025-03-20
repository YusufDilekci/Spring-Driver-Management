FROM openjdk:17-jdk-slim

# JAR dosyasını konteynere kopyalayoruz
COPY build/libs/assignment-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]