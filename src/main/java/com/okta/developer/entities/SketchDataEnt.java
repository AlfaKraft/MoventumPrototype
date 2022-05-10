package com.okta.developer.entities;

import javax.persistence.*;


@Entity
@Table(name = "sketchdata", schema = "restapi")
public class SketchDataEnt {
    private long id;
    private String sketchName;
    private int sketchWidth;
    private int sketchHeight;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSketchName() {
        return sketchName;
    }

    public void setSketchName(String name) {
        this.sketchName = name;
    }

    public int getSketchWidth() {
        return sketchWidth;
    }

    public void setSketchWidth(int sketchWidth) {
        this.sketchWidth = sketchWidth;
    }

    public int getSketchHeight() {
        return sketchHeight;
    }

    public void setSketchHeight(int sketchHeight) {
        this.sketchHeight = sketchHeight;
    }
}

