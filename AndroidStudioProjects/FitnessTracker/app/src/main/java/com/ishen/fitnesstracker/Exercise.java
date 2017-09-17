package com.ishen.fitnesstracker;

/**
 * Created by WingsOfRetribution on 2017-09-16.
 */

public class Exercise {
    public String name;
    public String box1;
    public String box2;

    public Exercise(String name, String box1, String box2) {
        this.name = name;
        this.box1 = box1;
        this.box2 = box2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBox1() {
        return box1;
    }

    public void setBox1(String box1) {
        this.box1 = box1;
    }

    public String getBox2() {
        return box2;
    }

    public void setBox2(String box2) {
        this.box2 = box2;
    }
}
