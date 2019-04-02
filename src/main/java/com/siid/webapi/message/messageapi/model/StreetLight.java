package com.siid.webapi.message.messageapi.model;

public class StreetLight {
    private float current;
    private int level;
    private StreetLightRule rule;
    private Integer ruleId;
    private float volt;

    private Integer id;
    private boolean isOffline;
    private boolean isError;
    private GeoPoint point;
    private String name;
    private String description;
    private String positionNumber;

    @Override
    public String toString() {
        return "id:"+this.id
                +" name:"+this.name
                +" description:"+this.description
                +" positionNumber:"+this.positionNumber
                +" current:"+this.current
                +" level:"+this.level
                +" rule"+this.rule
                +" ruleId"+this.ruleId;
    }

    public StreetLight(){
    }

    public StreetLight(Integer id, boolean isOffline, boolean isError, GeoPoint point, String name, String description, String positionNumber,
                       int level, float volt, float current, StreetLightRule rule) {
        this.id=id;
        this.isOffline=isOffline;
        this.isError=isError;
        this.point=point;
        this.name=name;
        this.description=description;
        this.positionNumber=positionNumber;
        this.level = level;
        this.volt = volt;
        this.current = current;
        this.rule = rule;
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

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public StreetLightRule getRule() {
        return rule;
    }

    public void setRule(StreetLightRule rule) {
        this.rule = rule;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public float getVolt() {
        return volt;
    }

    public void setVolt(float volt) {
        this.volt = volt;
    }
}
