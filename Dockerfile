FROM amazoncorretto:17.0.1
EXPOSE 8092
COPY build/libs/*.jar shapes.jar
CMD java -jar shapes.jar