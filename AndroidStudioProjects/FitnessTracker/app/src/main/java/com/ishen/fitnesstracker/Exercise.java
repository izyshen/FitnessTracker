package com.ishen.fitnesstracker;

/**
 * Created by WingsOfRetribution on 2017-09-16.
 */

public class Exercise {
    public String disp_name;
    public String disp_box1;
    public String disp_box2;

    public Exercise(String name, String box1, String box2) {
        this.disp_name = name;
        this.disp_box1 = box1;
        this.disp_box2 = box2;
    }

    public String getDisp_name() {
        return disp_name;
    }

    public void setDisp_name(String disp_name) {
        this.disp_name = disp_name;
    }

    public String getDisp_box1() {
        return disp_box1;
    }

    public void setDisp_box1(String disp_box1) {
        this.disp_box1 = disp_box1;
    }

    public String getDisp_box2() {
        return disp_box2;
    }

    public void setDisp_box2(String disp_box2) {
        this.disp_box2 = disp_box2;
    }
}
