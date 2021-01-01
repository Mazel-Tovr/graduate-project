FROM openjdk:11.0.6-jre

RUN mkdir /cart-service

COPY ./build/libs/cart-service-0.0.1-SNAPSHOT.jar /cart-service/cart-service.jar

WORKDIR /cart-service

CMD ["java", "-server", "-jar", "cart-service.jar", "--spring.profiles.active=docker"]
