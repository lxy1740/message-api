package com.siid.webapi.message.messageapi.service.Impl;


import com.siid.webapi.message.messageapi.service.UserTokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserTokenServiceImpl implements UserTokenService {
    @Autowired
    HttpServletRequest request;

    @Override
    public UUID getUserId() {
        Claims claims = (Claims)request.getAttribute("claims");
        if (claims != null && claims.containsKey("userid")) {
            return UUID.fromString((String)claims.get("userid"));
        }
        return null;
    }

    @Override
    public Integer getCustomerId() {
        Claims claims = (Claims)request.getAttribute("claims");
        if (claims != null && claims.containsKey("customerid")) {
            return (Integer) claims.get("customerid");
        }
        return null;
    }
}
