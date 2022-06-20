FROM openjdk:8-jre-alpine
WORKDIR /app
COPY demo-blog-web/target/demo-blog-web-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "demo-blog-web-0.0.1-SNAPSHOT.jar"]
EXPOSE 8087