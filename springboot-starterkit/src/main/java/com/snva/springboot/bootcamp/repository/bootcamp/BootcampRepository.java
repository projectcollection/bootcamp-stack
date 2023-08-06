package com.snva.springboot.bootcamp.repository.bootcamp;


import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BootcampRepository extends MongoRepository<Bootcamp,String> {


    List<Bootcamp> findByName(String name);

}
