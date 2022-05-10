package com.okta.developer.controllers;

import com.okta.developer.dao.SketchDao;
import com.okta.developer.dao.SketchDataDAO;
import com.okta.developer.dto.SketchDataDTO;
import com.okta.developer.dto.SketchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PostSchema {
    @Autowired
    private SketchDataDAO sketchDataDao;

    @PostMapping("/")
    @CrossOrigin("*")
    public void input(@RequestBody SketchDataDTO input){
        sketchDataDao.save(input.sketchDtoToSketchEnt());
    }


}
