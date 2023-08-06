package com.snva.springboot.bootcamp.repository.bootcamp;

import com.snva.springboot.bootcamp.model.bootcamp.Session;
import com.snva.springboot.bootcamp.model.bootcamp.SessionItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionItemRepository extends MongoRepository<SessionItem, String> {

    List<Session> findByName(String name);
}
