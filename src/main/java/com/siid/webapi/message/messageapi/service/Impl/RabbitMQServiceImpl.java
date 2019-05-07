package com.siid.webapi.message.messageapi.service.Impl;

import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public String createQueue(QueueArgs args, Integer customerId) throws IOException, TimeoutException {
        try {
            String queueName = "device.customer_" + customerId + "." + args.getInfoType() + "." + args.getConsumerId();
            Map<String, Object> argurements = new HashMap<>();
            argurements.put("x-message-ttl", 180000);
            Queue queue = new Queue(queueName, false, false, true, argurements);
            amqpAdmin.declareQueue(queue);


            String routingKey = "device.customer_" + customerId + "." + args.getInfoType() + ".*";//infoType:state,light,air,manhole
            Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, "device.state", routingKey, null);
            amqpAdmin.declareBinding(binding);
            return queueName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
