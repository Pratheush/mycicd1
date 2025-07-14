FROM openjdk:21
EXPOSE 8080
ADD target/spring-customer-mgmt-cicd.jar spring-customer-mgmt-cicd.jar
ENTRYPOINT ["java","-jar","/spring-customer-mgmt-cicd.jar"]