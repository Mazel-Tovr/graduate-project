FROM openjdk:11.0.6-jre

RUN mkdir /authorization-service

COPY ./build/libs/authorization-service-0.0.1-SNAPSHOT.jar /authorization-service/authorization-service.jar

WORKDIR /authorization-service

CMD ["java", "-server", "-jar", "authorization-service.jar", "--spring.profiles.active=docker"]
