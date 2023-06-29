package com.snva.springboot.bootcamp.repository.bus;

import com.snva.springboot.bootcamp.model.bus.Agency;
import com.snva.springboot.bootcamp.model.bus.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface BusRepository extends MongoRepository<Bus, String> {
    Bus findByCode(String busCode);

    Bus findByCodeAndAgency(String busCode, Agency agency);
}
