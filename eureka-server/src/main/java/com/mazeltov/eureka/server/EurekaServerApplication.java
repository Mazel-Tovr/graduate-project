package com.mazeltov.eureka.server;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        SpringApplication.run(EurekaServerApplication.class, args);

    }



    @Bean
    CommandLineRunner init(ApplicationInfoManager applicationInfoManager) {
        return (args) -> {
            System.out.println(InetAddress.getLocalHost());
            System.out.println(applicationInfoManager.getInfo().getHomePageUrl());
            System.out.println(applicationInfoManager.getInfo().getIPAddr());
        };
    }

}
