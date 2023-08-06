package com.snva.springboot.bootcamp.repository.bus;

import com.snva.springboot.bootcamp.model.bus.Agency;
import com.snva.springboot.bootcamp.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface AgencyRepository extends MongoRepository<Agency, String> {
    Agency findByCode(String agencyCode);

    Agency findByOwner(User owner);

    Agency findByName(String name);
}
