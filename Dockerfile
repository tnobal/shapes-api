FROM amazoncorretto:17.0.1
EXPOSE 8080
COPY build/libs/*.jar app.jar
CMD java -jar app.jar

