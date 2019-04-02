package com.siid.webapi.message.messageapi.controller;

import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.repository.DeviceDetailRepository;
import com.siid.webapi.message.messageapi.service.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@Api(value = "向客户端推送消息服务")
public class WebSocketController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private SimpMessagingTemplate template;


    @Autowired
    WebSocketService webSocketService;


    @ApiOperation(value = "将路灯状态变化数据推送给客户端")
    @RabbitListener(queues = "light.change")
    @RabbitHandler
    public void sendLightInfoToUser(List<StreetLight> streetLights) {
        List<StreetLight> lights_siid = webSocketService.filterData(1, 2, streetLights);
        if (lights_siid.size() > 0) {
            //lights_siid.forEach(p -> System.out.println(p.toString()));
            this.template.convertAndSendToUser(1 + "", "queue/light", lights_siid);
        }

        List<StreetLight> lights_aa123 = webSocketService.filterData(2, 2, streetLights);
        if (lights_aa123.size() > 0) {
            //lights_aa123.forEach(p->System.out.println(p.toString()));
            this.template.convertAndSendToUser(2 + "", "queque/light", lights_aa123);
        }
    }

}
