FROM gradle:6.8.2-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM adoptopenjdk/openjdk8:alpine-jre
RUN mkdir /app
COPY --from=build /home/gradle/src/demo-blog-web/target/demo-blog-web-0.0.1-SNAPSHOT.jar /app/application.jar
CMD ["java", "-jar", "/app/application.jar"]
EXPOSE 8080