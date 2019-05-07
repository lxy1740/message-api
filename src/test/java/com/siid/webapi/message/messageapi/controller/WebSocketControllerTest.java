package com.siid.webapi.message.messageapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.siid.webapi.message.messageapi.SecurityApi;
import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
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



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebSocketControllerTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    private QueueArgs queueArgs;

    @Autowired
    RabbitMQService rabbitMQService;

    @Autowired
    MockMvc mvc;


    private String token = "";
    Logger logger = LoggerFactory.getLogger(getClass());
    @Before
    public void setup() {
        SecurityApi security = new SecurityApi();
        token = security.login("app", "123");
    }

    @Test
    public void createQueue() throws Exception {
        String url = "/msg_api/webSocket/createQueue";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(url)
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(new QueueArgs("light", 1)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        String rs = response.getContentAsString();
        logger.info("测试状态码：" + status);
        logger.info("测试结果：" + rs);


    }

    @Test
    public void testCreateQueueService()throws Exception{
        System.out.println(rabbitMQService.createQueue(new QueueArgs("light",5),1));
    }


}
