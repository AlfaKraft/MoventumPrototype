package com.okta.developer.dto;

import com.okta.developer.entities.SketchDataEnt;

public class SketchDataDTO {
    private long id;
    private String name;
    private int width;
    private int heightL;

    public SketchDataDTO(String name, int width, int heightL) {
        this.name = name;
        this.width= width;
        this.heightL = heightL;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return heightL;
    }

    public void setHeight(int height) {
        this.heightL = height;
    }

    public SketchDataEnt sketchDtoToSketchEnt(){
        SketchDataEnt sketchEnt = new SketchDataEnt();
        sketchEnt.setSketchName(this.name);
        sketchEnt.setSketchHeight(this.heightL);
        sketchEnt.setSketchWidth(this.width);
        return sketchEnt;
    }
}
