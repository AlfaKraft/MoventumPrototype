package com.okta.developer.controllers;

import com.okta.developer.dto.SketchDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetSchema {

    @GetMapping("schema/{id}")
    public void function(@PathVariable long id){
        System.out.println(id);
    }
}
