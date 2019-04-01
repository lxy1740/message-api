package com.siid.webapi.message.messageapi.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "device_detail")
public class DeviceDetailEntity implements Serializable {
    private Integer id;
    private Integer modelId;
    private Integer parentId;
    private String name;
    private String description;
    private String iotId;
    private String secret;
    private Integer installMethodId;
    private Integer positionId;
    private BigDecimal lng;
    private BigDecimal lat;
    private boolean isActive;
    private boolean isOnline;
    private boolean isError;
    private boolean isDisabled;
    private String addUser;
    private Timestamp addTime;
    private String lastUser;
    private Timestamp lastTime;
    private Integer customerId;




    @Id
    @NotFound(action=NotFoundAction.IGNORE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name="customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Basic
    @Column(name = "iot_id")
    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    @Basic
    @Column(name = "secret")
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Basic
    @Column(name = "lng")
    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    @Basic
    @Column(name = "lat")
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_online")
    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    @Basic
    @Column(name = "is_error")
    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @Basic
    @Column(name = "is_disabled")
    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDetailEntity that = (DeviceDetailEntity) o;
        return isActive == that.isActive &&
                isOnline == that.isOnline &&
                isError == that.isError &&
                isDisabled == that.isDisabled &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(iotId, that.iotId) &&
                Objects.equals(secret, that.secret) &&
                Objects.equals(lng, that.lng) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(lastUser, that.lastUser) &&
                Objects.equals(lastTime, that.lastTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, iotId, secret, lng, lat, isActive, isOnline, isError, isDisabled, addUser, addTime, lastUser, lastTime);
    }

    @Basic
    @Column(name = "model_id")
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "install_method_id")
    public Integer getInstallMethodId() {
        return installMethodId;
    }

    public void setInstallMethodId(Integer installMethodId) {
        this.installMethodId = installMethodId;
    }

    @Basic
    @Column(name = "position_id")
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    private DeviceModelEntity model;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", insertable = false, updatable = false)
    public DeviceModelEntity getModel() {
        return model;
    }

    public void setModel(DeviceModelEntity model) {
        this.model = model;
    }

    private DevicePositionEntity Position;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    public DevicePositionEntity getPosition() {
        return Position;
    }

    public void setPosition(DevicePositionEntity position) {
        Position = position;
    }

}
