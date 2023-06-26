# Estágio de compilação
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de pacote
FROM openjdk:11-jdk-slim
COPY --from=build target/biblioteca-0.0.1-SNAPSHOT.jar /app/biblioteca.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/biblioteca.jar"]
