package com.siid.webapi.message.messageapi.model;


public class QueueArgs {
    private String deviceType;
    private String infoType;
    private int consumerId;

    public QueueArgs() {

    }

    public QueueArgs(String deviceType, String infoType, int consumerId) {
        this.deviceType = deviceType;
        this.infoType = infoType;
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

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}
