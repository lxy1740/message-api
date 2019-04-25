package com.siid.webapi.message.messageapi.model;


import java.util.UUID;

public class QueueArgs {
    private String infoType;
    private int consumerId;

    public QueueArgs() {

    }

    public QueueArgs(String infoType, int consumerId) {
        this.infoType = infoType;
        this.consumerId=consumerId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
}
