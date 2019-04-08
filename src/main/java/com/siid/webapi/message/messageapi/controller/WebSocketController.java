package com.siid.webapi.message.messageapi.controller;

import com.siid.webapi.message.messageapi.Constants;
import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value=Constants.URI_API_PREFIX+Constants.URI_WEBSOCKET)
@Api(value = "向客户端推送消息服务")
public class WebSocketController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    SimpMessageSendingOperations sendOperations;

    @Autowired
    UserTokenService userTokenService;
    private Integer customerId;


    @ApiOperation(value = "将路灯状态变化数据推送给客户端")
    @RabbitListener
    @RabbitHandler
    public void sendToUser(Message message) {
        System.out.println("接收到的消息："+message.getBody().toString());
    }

    @ApiOperation(value="动态创建队列")
    @PostMapping(value="/create/queue",produces = "application/json")
    public void createQueue(@RequestBody QueueArgs queueArgs){
        customerId=userTokenService.getCustomerId();
        String queueName=queueArgs.getDeviceType()+"."+customerId+"."+queueArgs.getInfoType()+"."+queueArgs.getConsumerId();
        Map<String,Object>args=new HashMap<>();
        args.put("x-message-ttl",180000);
        Queue queue=new Queue(queueName,true,false,true,args);
        amqpAdmin.declareQueue(queue);

        String routingKey=queueArgs.getDeviceType()+"."+customerId+"."+queueArgs.getInfoType()+".#";
        Binding binding=new Binding(queueName,Binding.DestinationType.QUEUE,"device."+queueArgs.getInfoType(),routingKey,null);
        amqpAdmin.declareBinding(binding);
    }

    @ApiOperation(value="动态删除队列")
    @DeleteMapping(value="/delete/queue",produces = "application/json")
    public void deleteQueue(@RequestBody QueueArgs queueArgs){
        String queueName=queueArgs.getDeviceType()+"."+customerId+"."+queueArgs.getInfoType()+"."+queueArgs.getConsumerId();
        amqpAdmin.deleteQueue(queueName);
        String routingKey=queueArgs.getDeviceType()+"."+customerId+"."+queueArgs.getInfoType()+".#";
        Binding binding=new Binding(queueName,Binding.DestinationType.QUEUE,"device."+queueArgs.getInfoType(),routingKey,null);
        amqpAdmin.removeBinding(binding);
    }

}
