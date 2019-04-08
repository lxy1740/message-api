package com.siid.webapi.message.messageapi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class SecurityApi {
    public String login(String userName, String password) {
        String url = "http://172.18.1.134:10003/security/login";
        RestTemplate restTemplate = new RestTemplate();
        String params = String.format("{ \"userName\":\"%s\", \"password\":\"%s\" }", userName, password);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        HttpEntity<String> entity = new HttpEntity<String>(params, headers);
        String token = restTemplate.postForObject(url, entity, String.class);
        return token;
    }
}
