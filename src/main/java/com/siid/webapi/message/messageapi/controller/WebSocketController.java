package com.siid.webapi.message.messageapi.controller;
import com.siid.webapi.message.messageapi.Constants;
import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import com.siid.webapi.message.messageapi.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 文件名: WebSocketController.java
 用途:   消息服务控制器，提供消息服务的Rest Api
 创建者: 廖晓音       创建日期: 2019-4-15
 修改者: 廖晓音       修改日期: 2019-5-6      修改内容: 修改createQueue输出参数类型
 */
@CrossOrigin
@RestController
@RequestMapping(value=Constants.URI_API_PREFIX+Constants.URI_WEBSOCKET)
@Api(value = "向客户端推送消息服务")
public class WebSocketController {

    /**
     * 用户token服务，获取用户id，客户id
    */
    @Autowired
    UserTokenService userTokenService;
    private Integer customerId;

    /**
     * 消息队列服务，动态创建、删除队列
     * */
    @Autowired
    RabbitMQService rabbitMQService;



    /**
     * 动态创建队列接口，映射到url:/msg_api/webSocket/createQueue，用POST方法调用
     * @param  QueueArgs 输入参数，获取创建队列参数：消费者id，队列传输消息类型
     * @throws Exception 创建队列失败时抛出异常
     */
    @ApiOperation(value="动态创建队列")
    @PostMapping(value = "/createQueue", produces = "application/json")
    public QueueArgs createQueue(@RequestBody QueueArgs queueArgs) throws Exception {
        customerId=userTokenService.getCustomerId();
        String str = rabbitMQService.createQueue(queueArgs, customerId);
        queueArgs.setQueueName(str);
        return queueArgs;
    }


}
