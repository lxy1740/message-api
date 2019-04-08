package com.siid.webapi.message.messageapi.controllerTest;

import com.siid.webapi.message.messageapi.SecurityApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSocketControllerTest {

    private String token="";
    private SecurityApi securityApi=new SecurityApi();

    @Before
    public void setUp(){
        token=securityApi.login("app1","123");
    }

    @Test
    public void createQueue(){

    }
}
