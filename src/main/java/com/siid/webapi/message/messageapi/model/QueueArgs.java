package com.siid.webapi.message.messageapi.model;


import java.util.UUID;

public class QueueArgs {
    private String deviceType;
    private String infoType;
    private UUID consumerId;

    public QueueArgs() {

    }

    public QueueArgs(String deviceType, String infoType,UUID consumerId) {
        this.deviceType = deviceType;
        this.infoType = infoType;
        this.consumerId=consumerId;
    }

    public UUID getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(UUID consumerId) {
        this.consumerId = consumerId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}
