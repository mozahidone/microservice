FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8084
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.yml"]