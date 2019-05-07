package com.siid.webapi.message.messageapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableRabbit
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class MessageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApiApplication.class, args);
    }

}
