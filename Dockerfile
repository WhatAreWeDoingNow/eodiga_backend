#FROM gradle:8.5-jdk17 AS builder
#WORKDIR /build
#COPY . .
#RUN gradle build -x test
#
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=builder /build/build/libs/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
