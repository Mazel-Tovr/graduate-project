FROM openjdk:11.0.6-jre

RUN mkdir /order-service

COPY ./build/libs/order-service-0.0.1-SNAPSHOT.jar /order-service/order-service.jar

WORKDIR /order-service

CMD ["java", "-server", "-jar", "order-service.jar", "--spring.profiles.active=docker"]
