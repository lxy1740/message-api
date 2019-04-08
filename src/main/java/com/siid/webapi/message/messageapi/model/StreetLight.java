package com.siid.webapi.message.messageapi.model;

public class StreetLight extends DeviceExt {
    private float current;
    private int level;
    private StreetLightRule rule;
    private Integer ruleId;
    private float volt;

    public StreetLight(Integer id, boolean isOffline, boolean isError, GeoPoint point, String name, String description, String positionNumber,
                       int level, float volt, float current, StreetLightRule rule) {
        super(id, isOffline, isError, point, name, description, positionNumber);
        this.level = level;
        this.volt = volt;
        this.current = current;
        this.rule = rule;
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
