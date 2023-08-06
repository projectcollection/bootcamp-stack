package com.snva.springboot.bootcamp.repository.bus;

import com.snva.springboot.bootcamp.model.bus.Trip;
import com.snva.springboot.bootcamp.model.bus.TripSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface TripScheduleRepository extends MongoRepository<TripSchedule, String> {
    TripSchedule findByTripDetailAndTripDate(Trip tripDetail, String tripDate);
}