package com.snva.springboot.bootcamp.model.bootcamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "sessionItem")
public class SessionItem {
    @Id
    String id;
    SessionType sessionType;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    String name;
    String details;
    String sessionLink;
    String sessionContentRecorded;

}
