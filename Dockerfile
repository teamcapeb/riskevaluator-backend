FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11
COPY --from=build /home/app/target/riskevaluator-backend.jar /usr/local/lib/riskevaluator-backend.jar
EXPOSE 8080
ENV JAVA_OPTS="-Xmx512m"
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "-Dserver.port=$PORT", "/usr/local/lib/riskevaluator-backend.jar"]
