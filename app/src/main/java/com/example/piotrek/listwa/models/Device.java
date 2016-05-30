package com.example.piotrek.listwa.models;

import java.util.ArrayList;

/**
 * Created by Piotrek on 10.05.2016.
 */
public class Device {
    public int id;
    public String name;
    public float value;

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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Device(int id, String name, float value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public static class List extends ArrayList<Device>{

    }
}
