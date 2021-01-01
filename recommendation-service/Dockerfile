FROM openjdk:11.0.6-jre

RUN mkdir /recommendation-service

COPY ./build/libs/recommendation-service-0.0.1-SNAPSHOT.jar /recommendation-service/recommendation-service.jar

WORKDIR /recommendation-service

CMD ["java", "-server", "-jar", "recommendation-service.jar", "--spring.profiles.active=docker"]
