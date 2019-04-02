package com.siid.webapi.message.messageapi.ControllerTest;

import com.siid.webapi.message.messageapi.controller.WebSocketController;
import com.siid.webapi.message.messageapi.model.GeoPoint;
import com.siid.webapi.message.messageapi.model.StreetLight;
import com.siid.webapi.message.messageapi.model.StreetLightRule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSocketControllerTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    WebSocketController webSocketController;

    private List<StreetLight> streetLights = new ArrayList<>();

    @Before
    public void setup() {
        for (int i = 21; i < 30; i++) {
            StreetLight light = new StreetLight(i, false, false, new GeoPoint(new BigDecimal(0), new BigDecimal(0))
                    , "test_light100001", "test_description", "", i, new Float(i), new Float(i), new StreetLightRule(i, "test_rule"));
            streetLights.add(light);
        }
        rabbitTemplate.convertAndSend("light", "light.change", streetLights);

    }


    @Test
    public void getFilterData() {
        List<StreetLight> streetLights1 = new ArrayList<>();
        webSocketController.sendLightInfoToUser(streetLights1);

    }
}
