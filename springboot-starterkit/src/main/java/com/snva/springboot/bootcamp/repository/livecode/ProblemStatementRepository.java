package com.snva.springboot.bootcamp.repository.livecode;

import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemStatementRepository extends MongoRepository<ProblemStatement,String> {


    Optional<ProblemStatement> findById(String id);

}
