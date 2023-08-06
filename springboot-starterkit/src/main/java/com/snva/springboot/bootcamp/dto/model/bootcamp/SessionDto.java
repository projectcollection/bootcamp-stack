package com.snva.springboot.bootcamp.dto.model.bootcamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)

public class SessionDto {
    String id;
    String name;
    Date SessionDate;
    List<SessionItemDto> sessionItems;
}