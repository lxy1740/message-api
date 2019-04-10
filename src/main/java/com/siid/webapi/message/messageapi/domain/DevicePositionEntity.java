package com.siid.webapi.message.messageapi.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "device_position")
public class DevicePositionEntity {
    private Integer id;
    private Integer positionTypeId;
    private String name;
    private String number;
    private Integer installZoneId;
    private String regionId;
    private BigDecimal lng;
    private BigDecimal lat;
    private String addUser;
    private Timestamp addTime;
    private String lastUser;
    private Timestamp lastTime;
    private Integer wayId;
    private Integer customerId;

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
    @Column(name="customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "position_type_id")
    public Integer getPositionTypeId() {
        return positionTypeId;
    }
    public void setPositionTypeId(Integer positionTypeId) {
        this.positionTypeId = positionTypeId;
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
    @Column(name = "number")
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "install_zone_id")
    public Integer getInstallZoneId() {
        return installZoneId;
    }
    public void setInstallZoneId(Integer installZoneId) {
        this.installZoneId = installZoneId;
    }

    @Basic
    @Column(name = "region_id")
    public String getRegionId() {
        return regionId;
    }
    public void setRegionId(String regionId) {
        this.regionId = regionId;
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
    @Column(name = "way_id")
    public Integer getWayId() {
        return wayId;
    }
    public void setWayId(Integer wayId) {
        this.wayId = wayId;
    }


    private GeoWayEntity geoWay;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "way_id", insertable = false, updatable = false)
    public GeoWayEntity getGeoWay() {
        return geoWay;
    }
    public void setGeoWay(GeoWayEntity geoWay) {
        this.geoWay = geoWay;
    }

    private DeviceInstallZoneEntity InstallZone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "install_zone_id", insertable = false, updatable = false)
    public DeviceInstallZoneEntity getInstallZone() {
        return InstallZone;
    }
    public void setInstallZone(DeviceInstallZoneEntity installZone) {
        InstallZone = installZone;
    }

    private GeoRegionEntity region;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    public GeoRegionEntity getRegion() {
        return region;
    }
    public void setRegion(GeoRegionEntity region) {
        this.region = region;
    }

    private CustomerEntity customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<DeviceDetailEntity> deviceDetails;
    @OneToMany
    @JoinColumn(name = "position_id")
    public List<DeviceDetailEntity> getDeviceDetails() {
        return deviceDetails;
    }
    public void setDeviceDetails(List<DeviceDetailEntity> deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePositionEntity that = (DevicePositionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(positionTypeId, that.positionTypeId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(number, that.number) &&
                Objects.equals(installZoneId, that.installZoneId) &&
                Objects.equals(regionId, that.regionId) &&
                Objects.equals(lng, that.lng) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(addUser, that.addUser) &&
                Objects.equals(addTime, that.addTime) &&
                Objects.equals(lastUser, that.lastUser) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(geoWay, that.geoWay) &&
                Objects.equals(InstallZone, that.InstallZone) &&
                Objects.equals(region, that.region) &&
                Objects.equals(deviceDetails, that.deviceDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionTypeId, name, number, installZoneId, regionId, lng, lat, addUser,
                addTime, lastUser, lastTime, geoWay, InstallZone, region, deviceDetails);
    }
}
