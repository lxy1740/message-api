package com.siid.webapi.message.messageapi.controllerTest;

import com.rabbitmq.client.Channel;
import com.siid.webapi.message.messageapi.Constants;
import com.siid.webapi.message.messageapi.SecurityApi;
import com.siid.webapi.message.messageapi.model.GeoPoint;
import com.siid.webapi.message.messageapi.model.QueueArgs;


import com.alibaba.fastjson.JSONObject;

import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.model.StreetLightRule;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebSocketControllerTest {

    private String token="";
    private SecurityApi securityApi=new SecurityApi();
    private static final String URI= Constants.URI_API_PREFIX+Constants.URI_WEBSOCKET;
    private Logger logger=LoggerFactory.getLogger(getClass());

    @Autowired
    private MockMvc mvc;

    private QueueArgs args;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitMQService rabbitMQService;

    @Before
    public void setUp(){
        token=securityApi.login("new","123");
        args=new QueueArgs("device","state", UUID.randomUUID());

        Exchange exchange=new TopicExchange("device.state",true,false,null);
        amqpAdmin.declareExchange(exchange);

        StreetLight light=new StreetLight(1,false,false,
                new GeoPoint(new BigDecimal(113),new BigDecimal(114)),"1006路灯","jasgd","xa",3,
                new Float(5),new Float(6),new StreetLightRule(5,"xbae"));
        rabbitTemplate.convertAndSend("device.state", "device.1.state.#", light);
    }


    @Test
    public void sendMessage()throws Exception{
        rabbitTemplate.convertAndSend("device.state","device.state.test","");
    }

    @Test
    public void createQueue()throws Exception{
        String url=URI+"/create/queue";
        MvcResult result=mvc.perform(MockMvcRequestBuilders.post(url)
        .header("Authorization","Bearer "+token)
        .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(args)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response=result.getResponse();
        int status=response.getStatus();
        logger.info("测试createQueue接口，动态创建队列——————");

    }

    @Test
    public void receiveMessage()throws Exception{
        Message m1 = rabbitTemplate.receive("device.state");
        Message m2 = rabbitTemplate.receive("device.state");
        Message m3=rabbitTemplate.receive("device.state");
    }
}
