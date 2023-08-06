package com.snva.springboot.bootcamp.repository.bus;

import com.snva.springboot.bootcamp.model.bus.Agency;
import com.snva.springboot.bootcamp.model.bus.Bus;
import com.snva.springboot.bootcamp.model.bus.Stop;
import com.snva.springboot.bootcamp.model.bus.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Arpit Khandelwal.
 */
public interface TripRepository extends MongoRepository<Trip, String> {
    Trip findBySourceStopAndDestStopAndBus(Stop source, Stop destination, Bus bus);

    List<Trip> findAllBySourceStopAndDestStop(Stop source, Stop destination);

    List<Trip> findByAgency(Agency agency);
}
