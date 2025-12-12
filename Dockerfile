FROM ubuntu:latest AS builder

RUN apt-get update \
  && apt-get install -y openjdk-17-jdk maven

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk AS runtime
EXPOSE 8080

WORKDIR /app
COPY --from=builder /app/target/job_platform-0.0.1-SNAPSHOT.jar job_platform.jar

ENTRYPOINT ["java", "-jar", "job_platform.jar"]