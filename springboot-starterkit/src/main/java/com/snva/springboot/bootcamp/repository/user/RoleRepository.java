package com.snva.springboot.bootcamp.repository.user;

import com.snva.springboot.bootcamp.model.user.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);

}
