package com.example.piotrek.listwa.models;

/**
 * Created by Piotrek on 03.06.2016.
 */
public class DigitalResponse {

    private String message;
    private Boolean connected;
    private Integer id;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
