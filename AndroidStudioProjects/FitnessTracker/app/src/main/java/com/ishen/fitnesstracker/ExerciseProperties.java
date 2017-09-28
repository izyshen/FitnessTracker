package com.ishen.fitnesstracker;

/**
 * Created by WingsOfRetribution on 2017-09-28.
 */

public class ExerciseProperties {
    public String disp_name;
    public int weightbox, setbox, repbox, timebox, speedbox, restbox;

    public ExerciseProperties(String name, int weight, int set, int rep, int time, int speed, int rest) {
        this.disp_name = name;
        this.weightbox = weight;
        this.setbox = set;
        this.repbox = rep;
        this.timebox = time;
        this.speedbox = speed;
        this.restbox = rest;
    }

    public String getDisp_name() {
        return disp_name;
    }

    public int getWeightbox() {
        return weightbox;
    }

    public int getSetbox() {
        return setbox;
    }

    public int getRepbox() {
        return repbox;
    }

    public int getTimebox() {
        return timebox;
    }

    public int getSpeedbox() {
        return speedbox;
    }

    public int getRestbox() {
        return restbox;
    }
}
