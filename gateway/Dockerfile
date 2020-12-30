FROM openjdk:8-jre-alpine

RUN mkdir /gateway

COPY ./build/libs/gateway-0.0.1-SNAPSHOT.jar /gateway/gateway.jar

WORKDIR /gateway

CMD ["java", "-server", "-jar", "gateway.jar", "--spring.profiles.active=docker"]
