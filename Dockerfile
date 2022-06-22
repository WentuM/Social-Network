FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./devops-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "devops-0.0.1-SNAPSHOT.jar"]
EXPOSE 8086