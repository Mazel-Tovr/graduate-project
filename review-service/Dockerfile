FROM openjdk:11.0.6-jre

RUN mkdir /review-service

COPY ./build/libs/review-service-0.0.1-SNAPSHOT.jar /review-service/review-service.jar

WORKDIR /review-service

CMD ["java", "-server", "-jar", "review-service.jar", "--spring.profiles.active=docker"]
