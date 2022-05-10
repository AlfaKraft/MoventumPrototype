package com.okta.developer.dto;

import com.okta.developer.entities.SketchEnt;

public class SketchDto {
    private long id;
    private String name;
    private String description;

    public SketchDto(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SketchEnt sketchDtoToSketchEnt(){
        SketchEnt sketchEnt = new SketchEnt();
        sketchEnt.name = this.name;
        sketchEnt.description = this.description;
        return sketchEnt;
    }
}
