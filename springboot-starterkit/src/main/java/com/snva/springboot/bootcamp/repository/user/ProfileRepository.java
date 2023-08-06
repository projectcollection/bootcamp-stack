package com.snva.springboot.bootcamp.repository.user;

import com.snva.springboot.bootcamp.model.user.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by Arpit Khandelwal.
 */
public interface ProfileRepository extends MongoRepository<UserProfile, String> {


    // convention over configuration

    //findBy
    Optional<UserProfile> findByName(String rpofileIdentifier);



}
