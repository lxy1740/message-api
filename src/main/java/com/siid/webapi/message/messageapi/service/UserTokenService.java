package com.siid.webapi.message.messageapi.service;

import java.util.UUID;

/**
 * 用户Token服务，用于从登录的上下文中获取token中保存的信息
 */
public interface UserTokenService {
    /**
     * 获取当前登录用户id
     * @return
     */
    UUID getUserId();

    /**
     * 获取当前登录用户的客户id
     * @return
     */
    Integer getCustomerId();
}
