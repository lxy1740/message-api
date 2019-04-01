package com.siid.webapi.message.messageapi.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "device_type")
public class DeviceTypeEntity {
    private Integer id;
    private String name;
    private String addUser;
    private Timestamp addTime;
    private String lastUser;
    private Timestamp lastTime;


    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        DeviceTypeEntity that = (DeviceTypeEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(lastUser, that.lastUser) &&
                Objects.equals(lastTime, that.lastTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, addUser, addTime, lastUser, lastTime);
    }
}
