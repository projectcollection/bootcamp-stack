package com.snva.springboot.bootcamp.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Project {
    private String name;
    private  String techStack;
    private String roles;
    private  String responsibilities;
    private  Number teamSize;
    private String details;
}
