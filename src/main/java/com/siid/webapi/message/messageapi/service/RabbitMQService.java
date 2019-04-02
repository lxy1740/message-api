package com.siid.webapi.message.messageapi.service;

import com.siid.webapi.message.messageapi.domain.DeviceDetailEntity;
import com.siid.webapi.message.messageapi.domain.DeviceModelEntity;
import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.repository.DeviceDetailRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RabbitMQService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    DeviceDetailRepository deviceDetailRepository;


    @RabbitHandler
    @RabbitListener(queues = "light.change")
    public void getFilterData(List<StreetLight> streetLights) {
        List<StreetLight> lights_siid = streetLights.stream()
                .filter(p -> deviceDetailRepository.getOne(p.getId()).getCustomerId() == 1
                        && deviceDetailRepository.getOne(p.getId()).getModel().getDeviceTypeId() == 2)
                .collect(Collectors.toList());
        if (lights_siid.size() > 0) {
            rabbitTemplate.convertAndSend("light", "light.siid", lights_siid);
        }

        List<StreetLight> lights_aa123 = streetLights.stream()
                .filter(p -> deviceDetailRepository.getOne(p.getId()).getCustomerId() == 2
                        && deviceDetailRepository.getOne(p.getId()).getModel().getDeviceTypeId() == 2)
                .collect(Collectors.toList());
        if (lights_aa123.size() > 0) {
            rabbitTemplate.convertAndSend("light", "light.aa123", lights_aa123);
        }

    }
}
