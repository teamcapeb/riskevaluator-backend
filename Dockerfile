FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} riskevaluator-backend.jar
#EXPOSE $PORT
#ENTRYPOINT ["java","-jar","/riskevaluator-backend.jar"]

ARG  JAR_FILE=target/*.jar
RUN mkdir -p /apps
COPY ${JAR_FILE} /apps/app.jar

CMD [ "sh", "-c", "java -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -jar /apps/app.jar" ]
