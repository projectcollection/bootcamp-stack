package com.snva.springboot.bootcamp.controller.v1.request.bootcamp;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionItemRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String sessionType;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String details;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String sessionLink;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String sessionContentRecorded;

}
