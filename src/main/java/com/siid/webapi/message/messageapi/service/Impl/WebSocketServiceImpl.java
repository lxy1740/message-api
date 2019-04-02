package com.siid.webapi.message.messageapi.service.Impl;

import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.repository.DeviceDetailRepository;
import com.siid.webapi.message.messageapi.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    @Autowired
    DeviceDetailRepository deviceDetailRepository;

    @Override
    public List<StreetLight> filterData(Integer customerId, int deviceTypeId, List<StreetLight> streetLights) {
        List<StreetLight> lights = streetLights.stream()
                .filter(p -> deviceDetailRepository.getOne(p.getId()).getCustomerId() == customerId
                        && deviceDetailRepository.getOne(p.getId()).getModel().getDeviceTypeId() == deviceTypeId)
                .collect(Collectors.toList());
        return lights;
    }
}
