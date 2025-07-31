# 1단계: 빌드용 이미지
FROM gradle:8.4-jdk17-alpine AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon

# 2단계: 실행용 이미지
FROM eclipse-temurin:17-jdk-alpine
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
