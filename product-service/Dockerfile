FROM openjdk:17
ARG service_version
COPY /target/product-service-$service_version.jar /usr/local/lib/app.jar
EXPOSE 8070
ARG buildno

RUN echo "Build No : $buildno"
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]