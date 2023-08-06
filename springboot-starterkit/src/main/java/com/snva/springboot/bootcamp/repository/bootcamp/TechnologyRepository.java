package com.snva.springboot.bootcamp.repository.bootcamp;

import com.snva.springboot.bootcamp.model.bootcamp.Technology;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends MongoRepository<Technology, String> {

    List<Technology> findByName(String name);
}
