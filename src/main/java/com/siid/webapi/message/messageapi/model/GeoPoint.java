package com.siid.webapi.message.messageapi.model;

import java.math.BigDecimal;

public class GeoPoint {
    private BigDecimal lng;
    private BigDecimal lat;

    public GeoPoint(BigDecimal lng, BigDecimal lat) {
        this.lng = lng;
        this.lat = lat;
    }
    public GeoPoint(){

    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
