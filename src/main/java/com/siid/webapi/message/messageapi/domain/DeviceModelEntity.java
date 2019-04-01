package com.siid.webapi.message.messageapi.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "device_model")
public class DeviceModelEntity {

    public enum IoTPlatform {
        UNKNOWN,
        ALI_IOT
    }

    private Integer id;
    private String key;
    private String name;
    private boolean isGateway;
    private String description;
    private String addUser;
    private Timestamp addTime;
    private String lastUser;
    private Timestamp lastTime;
    private Integer deviceTypeId;
    private Integer iotPlatform;

//    @OneToMany
//    private DeviceModelProperty deviceModelProperty;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "add_user")
    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    @Basic
    @Column(name = "add_time")
    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    @Basic
    @Column(name = "last_user")
    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    @Basic
    @Column(name = "last_time")
    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "is_gateway")
    public boolean isGateway() {
        return isGateway;
    }

    public void setGateway(boolean gateway) {
        isGateway = gateway;
    }

    @Basic
    @Column(name = "iot_platform")
    public Integer getIotPlatform() {
        return iotPlatform;
    }

    public void setIotPlatform(Integer iotPlatform) {
        this.iotPlatform = iotPlatform;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceModelEntity that = (DeviceModelEntity) o;
        return isGateway == that.isGateway &&
                Objects.equals(id, that.id) &&
                Objects.equals(key, that.key) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(lastUser, that.lastUser) &&
                Objects.equals(lastTime, that.lastTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, key, name, isGateway, description, addUser, addTime, lastUser, lastTime);
    }

    @Basic
    @Column(name = "device_type_id")
    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

//    public DeviceModelProperty getDeviceModelProperty() {
//        return deviceModelProperty;
//    }
//
//    public void setDeviceModelProperty(DeviceModelProperty deviceModelProperty) {
//        this.deviceModelProperty = deviceModelProperty;
//    }
    private DeviceTypeEntity deviceType;

    @ManyToOne
    @JoinColumn(name = "device_type_id", updatable = false, insertable = false)
    public DeviceTypeEntity getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeEntity deviceType) {
        this.deviceType = deviceType;
    }

}
