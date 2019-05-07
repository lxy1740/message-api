package com.siid.webapi.message.messageapi.service;

import com.siid.webapi.message.messageapi.model.QueueArgs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public interface RabbitMQService {
    String createQueue(QueueArgs args, Integer customerId) throws IOException, TimeoutException;



}
