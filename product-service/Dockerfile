FROM openjdk:11.0.6-jre

RUN mkdir /product-service

COPY ./build/libs/product-service-0.0.1-SNAPSHOT.jar /product-service/product-service.jar

WORKDIR /product-service

CMD ["java", "-server", "-jar", "product-service.jar"]
