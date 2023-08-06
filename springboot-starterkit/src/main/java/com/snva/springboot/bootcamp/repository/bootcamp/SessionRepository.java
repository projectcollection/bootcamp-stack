package com.snva.springboot.bootcamp.repository.bootcamp;

import com.snva.springboot.bootcamp.model.bootcamp.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {

    List<Session> findByName(String name);
}
