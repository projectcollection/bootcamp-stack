package com.snva.springboot.bootcamp.repository.user;

import com.snva.springboot.bootcamp.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);


}
