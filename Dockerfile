FROM openjdk:11
ARG  JAR_FILE=target/*.jar
COPY ./target/riskevaluator-backend-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
