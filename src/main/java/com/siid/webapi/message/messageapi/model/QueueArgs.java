package com.siid.webapi.message.messageapi.model;


public class QueueArgs {
    private String infoType;//标识传输不同设备信息的队列
    private int consumerId;//标识消费队列的消费者
    private String queueName;//输出队列的名称

    public QueueArgs() {

    }

    public QueueArgs(String infoType, int consumerId) {
        this.infoType = infoType;
        this.consumerId=consumerId;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
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
