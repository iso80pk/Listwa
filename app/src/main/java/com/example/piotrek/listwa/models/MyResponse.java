package com.example.piotrek.listwa.models;

import java.util.ArrayList;

/**
 * Created by Piotrek on 15.05.2016.
 */
public class MyResponse {
    private String message;
    private String id;
    private String name;
    private String hardware;
    private Boolean connected;


    public MyResponse(String message, String id, String name, String hardware, Boolean connected) {
        this.message = message;
        this.id = id;
        this.name = name;
        this.hardware = hardware;
        this.connected = connected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public class List extends ArrayList<MyResponse> {
    }
}
