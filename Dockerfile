# Step 1: Build with Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Lightweight runtime with JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render uses dynamic port
ENV PORT 10000
EXPOSE 10000

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
