package com.snva.springboot.bootcamp.dto.model.bootcamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class TechnologyDto {
    String id;
    String name;
    String vendorName;
    String version;
}
