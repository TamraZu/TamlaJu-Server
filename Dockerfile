FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} tamrazu.jar
ENTRYPOINT ["java","-jar","/tamrazu.jar"]