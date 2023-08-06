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
public class TechnologyRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String vendorName;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String version;
}
