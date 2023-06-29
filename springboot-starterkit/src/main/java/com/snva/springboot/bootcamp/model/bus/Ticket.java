package com.snva.springboot.bootcamp.model.bus;

import com.snva.springboot.bootcamp.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Arpit Khandelwal.
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;

    private int seatNumber;

    private boolean cancellable;

    private String journeyDate;

    @DBRef
    private TripSchedule tripSchedule;

    @DBRef
    private User passenger;
}
