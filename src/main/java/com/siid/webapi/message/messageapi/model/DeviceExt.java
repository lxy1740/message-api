package com.siid.webapi.message.messageapi.model;

public class DeviceExt {
    private Integer id;
    private boolean isOffline;
    private boolean isError;
    private GeoPoint point;
    private String name;
    private String description;
    private String positionNumber;

    public DeviceExt(Integer id, boolean isOffline, boolean isError, GeoPoint point, String name, String description, String positionNumber) {
        this.id = id;
        this.isOffline = isOffline;
        this.isError = isError;
        this.point = point;
        this.name = name;
        this.description = description;
        this.positionNumber = positionNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public GeoPoint getPoint() {
        return point;
    }

    public void setPoint(GeoPoint point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }
}
