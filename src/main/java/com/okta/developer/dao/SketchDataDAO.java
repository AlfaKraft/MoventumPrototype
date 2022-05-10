package com.okta.developer.dao;

import com.okta.developer.entities.SketchDataEnt;
import com.okta.developer.entities.SketchEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SketchDataDAO extends JpaRepository<SketchDataEnt, Long> {
    SketchDataEnt save(SketchDataEnt ent);
    List<SketchDataEnt> findAll();
}
