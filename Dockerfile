FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mkhidma-api.jar
ENTRYPOINT ["java","-jar","/mkhidma-api.jar"]
EXPOSE 8081