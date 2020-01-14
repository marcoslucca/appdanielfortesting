FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/customer-api*.jar customer-api.jar
ENTRYPOINT ["java", "-jar", "customer-api.jar"]