package com.siid.webapi.message.messageapi.service;

import com.siid.webapi.message.messageapi.model.QueueArgs;
import org.springframework.stereotype.Service;

@Service
public interface RabbitMQService {
    public String createQueue(QueueArgs args, Integer customerId);

    public void deleteQueue(QueueArgs args, Integer customerId);

}
