FROM openjdk:8-jre-alpine

RUN mkdir /eureka-server

COPY ./build/libs/eureka-server-0.0.1-SNAPSHOT.jar /eureka-server/eureka-server.jar

WORKDIR /eureka-server

CMD ["java", "-server", "-jar", "eureka-server.jar"]
