package com.siid.webapi.message.messageapi.model;

public class StreetLightRule {
    private int id;
    private String name;

    public StreetLightRule(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public StreetLightRule(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
