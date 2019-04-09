package com.siid.webapi.message.messageapi.service.Impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.siid.webapi.message.messageapi.model.QueueArgs;
import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.service.RabbitMQService;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void createQueue(QueueArgs args, Integer customerId) {
        String queueName = args.getDeviceType() + "."+customerId+"." + args.getInfoType()+"."+args.getConsumerId();
        Map<String, Object> argurements = new HashMap<>();
        argurements.put("x-message-ttl", 180000);
        Queue queue = new Queue(queueName, true, false, true, argurements);
        amqpAdmin.declareQueue(queue);

        String routingKey = args.getDeviceType() + "." + customerId + "." + args.getInfoType() + ".#";
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, "device." + args.getInfoType(), routingKey, null);
        amqpAdmin.declareBinding(binding);
    }


    @Override
    public void deleteQueue(QueueArgs args, Integer customerId) {
        String queueName = args.getDeviceType() + "." + customerId + "." + args.getInfoType()+"."+args.getConsumerId();
        amqpAdmin.deleteQueue(queueName);
        String routingKey = args.getDeviceType() + "." + customerId + "." + args.getInfoType()+".#";
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, "device." + args.getInfoType(), routingKey, null);
        amqpAdmin.removeBinding(binding);
    }
}
