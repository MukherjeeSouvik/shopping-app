#Start From Base Docker Image
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#Add application jar to the container
COPY target/product-orch-svc.jar product-orch-svc.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","product-orch-svc.jar"]