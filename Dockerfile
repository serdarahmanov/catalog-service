# Build stage
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /workspace
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} catalog-service.jar
RUN java -Djarmode=layertools -jar catalog-service.jar extract

# Runtime stage
FROM gcr.io/distroless/java17:nonroot
#RUN useradd spring
#USER spring
WORKDIR /workspace
COPY --from=builder /workspace/dependencies/ ./
COPY --from=builder /workspace/spring-boot-loader/ ./
COPY --from=builder /workspace/snapshot-dependencies/ ./
COPY --from=builder /workspace/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]











#FROM openjdk:24-jdk-oracle
#WORKDIR workspace
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} catalog-service.jar
#ENTRYPOINT ["java","-jar","catalog-service.jar"]