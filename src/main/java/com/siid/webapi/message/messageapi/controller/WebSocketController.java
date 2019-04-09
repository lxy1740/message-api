package com.siid.webapi.message.messageapi.controller;
import com.siid.webapi.message.messageapi.Constants;
import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import com.siid.webapi.message.messageapi.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value=Constants.URI_API_PREFIX+Constants.URI_WEBSOCKET)
@Api(value = "向客户端推送消息服务")
public class WebSocketController {

    @Autowired
    UserTokenService userTokenService;
    private Integer customerId;

    @Autowired
    RabbitMQService rabbitMQService;


    @ApiOperation(value="动态创建队列")
    @PostMapping(value="/create/queue",produces = "application/json")
    public void createQueue(@RequestBody QueueArgs queueArgs){
        customerId=userTokenService.getCustomerId();
        rabbitMQService.createQueue(queueArgs, customerId);
    }

    @ApiOperation(value="动态删除队列")
    @DeleteMapping(value="/delete/queue",produces = "application/json")
    public void deleteQueue(@RequestBody QueueArgs queueArgs){
        customerId = userTokenService.getCustomerId();
        rabbitMQService.deleteQueue(queueArgs, customerId);
    }

}
