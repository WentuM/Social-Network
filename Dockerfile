FROM openjdk:8-jre-alpine
WORKDIR /app
COPY ./devops-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "devops-0.0.1-SNAPSHOT.jar"]