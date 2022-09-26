FROM openjdk:11
EXPOSE 8082
ADD target/AdminServices.jar adminservices.jar
ENTRYPOINT ["java","-jar","adminservices.jar"]