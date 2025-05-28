# Stage 1: Build app với Maven
FROM maven:3.8.7-openjdk-17 AS build
WORKDIR /app

# Copy file cấu hình Maven và mã nguồn
COPY pom.xml .
COPY src ./src

# Build file jar, bỏ qua test cho nhanh
RUN mvn clean package -DskipTests

# Stage 2: Chạy app với OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy file jar từ giai đoạn build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
