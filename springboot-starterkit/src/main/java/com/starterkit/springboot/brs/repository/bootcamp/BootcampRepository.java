package com.starterkit.springboot.brs.repository.bootcamp;


import com.starterkit.springboot.brs.dto.model.bootcamp.BootcampDto;
import com.starterkit.springboot.brs.model.bootcamp.Bootcamp;
import com.starterkit.springboot.brs.service.IBootcampService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BootcampRepository extends MongoRepository<Bootcamp,String> {


    List<Bootcamp> findByName(String name);
   // List<Bootcamp> findByUserId(String id);
}
