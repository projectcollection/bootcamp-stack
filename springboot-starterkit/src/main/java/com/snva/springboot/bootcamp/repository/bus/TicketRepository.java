package com.snva.springboot.bootcamp.repository.bus;

import com.snva.springboot.bootcamp.model.bus.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Arpit Khandelwal.
 */
public interface TicketRepository extends MongoRepository<Ticket, String> {
}
