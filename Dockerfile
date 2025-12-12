FROM ubuntu:latest AS builder

RUN apt-get update 
RUN apt-get install openjdk-17-jdk -y 
COPY . .

RUN apt-get install maven -y
RUN mvn clean install 

FROM eclipse-temurin:17-jdk AS build
EXPOSE 8080

COPY --from=build /target/job_platform-0.0.1-SNAPSHOT.jar job_platform.jar

ENTRYPOINT [ "java", "-jar", "job_platform.jar" ]