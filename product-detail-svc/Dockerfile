#Start From Base Docker Image
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#Add application jar to the container
COPY target/product-detail-svc.jar product-detail-svc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","product-detail-svc.jar"]