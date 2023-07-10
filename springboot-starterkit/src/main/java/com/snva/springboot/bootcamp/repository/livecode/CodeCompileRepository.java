package com.snva.springboot.bootcamp.repository.livecode;


import com.snva.springboot.bootcamp.model.bootcamp.CodeCompile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeCompileRepository extends MongoRepository<CodeCompile,String> {


    Optional<CodeCompile> findById(String id);

}
