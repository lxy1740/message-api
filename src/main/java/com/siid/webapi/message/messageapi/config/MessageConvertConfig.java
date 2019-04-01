package com.siid.webapi.message.messageapi.config;

import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConvertConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2XmlMessageConverter();
    }
}
