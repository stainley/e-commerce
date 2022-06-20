FROM openjdk:17

COPY /target/user-service-0.0.1.jar /usr/local/lib/app.jar
EXPOSE 8090
ARG buildno

RUN echo "Build No : $buildno"
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]