package com.snva.springboot.bootcamp.repository.recruitment;


import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;
import com.snva.springboot.bootcamp.model.recruitment.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicantRepository extends MongoRepository<Applicant,String> {

    Optional<Applicant> findByPhone(String phone);

}
