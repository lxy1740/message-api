package com.siid.webapi.message.messageapi.service;

import com.siid.webapi.message.messageapi.model.StreetLight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebSocketService {

    List<StreetLight> filterData(Integer customerId, int deviceTypeId, List<StreetLight> streetLights);
}
