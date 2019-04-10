package com.siid.webapi.message.messageapi.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "geo_region")
@Proxy(lazy = false)
public class GeoRegionEntity {
    private String id;
    private String name;
    private Integer level;
    private String addUser;
    private Timestamp addTime;
    private String lastUser;
    private Timestamp lastTime;
    private BigDecimal lng;
    private BigDecimal lat;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
        GeoRegionEntity that = (GeoRegionEntity) o;
        return level == that.level &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(lastUser, that.lastUser) &&
                Objects.equals(lastTime, that.lastTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, level, addUser, addTime, lastUser, lastTime);
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
}
