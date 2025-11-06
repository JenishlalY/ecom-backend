# Step 1: Build with Maven + JDK 25
FROM maven:3.9.6-openjdk-25 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Lightweight Java runtime with JDK 25
FROM openjdk:25-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Use Render dynamic port
ENV PORT 10000
EXPOSE 10000

# Run with dynamic port
ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
