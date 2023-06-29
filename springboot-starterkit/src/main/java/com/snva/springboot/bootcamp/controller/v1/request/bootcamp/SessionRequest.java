package com.snva.springboot.bootcamp.controller.v1.request.bootcamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    Date SessionDate;
    List<SessionItemRequest> sessionItems;
}
