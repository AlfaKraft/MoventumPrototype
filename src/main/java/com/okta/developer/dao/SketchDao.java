package com.okta.developer.dao;

import com.okta.developer.entities.SketchEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SketchDao extends JpaRepository<SketchEnt, Long> {
    SketchEnt save(SketchEnt sketchEnt);
    List<SketchEnt> findAll();
}
