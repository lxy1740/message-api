package com.siid.webapi.message.messageapi.controller;

import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@Api(value = "向客户端推送消息服务")
public class WebSocketController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    SimpMessageSendingOperations sendOperations;


    @ApiOperation(value = "将路灯状态变化数据推送给客户端")
    @MessageMapping("/streetlight/change")
    public void sendToUser() {
        List<StreetLight> streetLightList = new ArrayList<>();
        //rabbitMQService.getFilterData();
        sendOperations.convertAndSend("topic/light.change", streetLightList);
    }

}
