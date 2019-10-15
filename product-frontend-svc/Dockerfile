#Start From Base Docker Image
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#Add application jar to the container
COPY target/product-frontend-svc.jar product-frontend-svc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","product-frontend-svc.jar"]