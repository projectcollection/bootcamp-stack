package com.snva.springboot.bootcamp.dto.model.bootcamp;

import com.snva.springboot.bootcamp.model.bootcamp.SessionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class SessionItemDto {
    String id;
    SessionType sessionType;
    String name;
    String details;
    String sessionLink;
    String sessionContentRecorded;

}
