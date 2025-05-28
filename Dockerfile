# Dùng image chính thức của OpenJDK 17
FROM openjdk:17-jdk-slim

# Tạo thư mục làm việc bên trong container
WORKDIR /app

# Copy file jar từ máy local vào thư mục /app trong container
COPY target/*.jar app.jar

# Mặc định chạy ứng dụng Spring Boot khi container start
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080