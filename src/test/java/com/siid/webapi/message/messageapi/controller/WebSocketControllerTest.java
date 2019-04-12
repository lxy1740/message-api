package com.siid.webapi.message.messageapi.controller;

import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSocketControllerTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    private QueueArgs queueArgs;

    @Autowired
    RabbitMQService rabbitMQService;


    @Before
    public void setup() {
        queueArgs=new QueueArgs("queue.test","state",UUID.fromString("9c15b664-2e4a-4a43-b446-cc24792eebc8"));
        //创建交换机
        Exchange exchange=new TopicExchange("device.state",true,false);
        amqpAdmin.declareExchange(exchange);
        rabbitTemplate.convertAndSend("device.state",queueArgs.getDeviceType()+".1."+queueArgs.getInfoType()+".#","hello world!");

    }


    @Test
    public void createQueue() {
        rabbitMQService.createQueue(queueArgs,1);
    }

    @Test
    public void deleteQueue(){
        rabbitMQService.deleteQueue(queueArgs,1);
    }

    @Test
    public void receiveMess(){
        rabbitTemplate.receive(queueArgs.getDeviceType()+".1."+queueArgs.getInfoType()+"."+queueArgs.getConsumerId());
    }
}
