package com.snva.springboot.bootcamp.model.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Skill {
    private String skillName;
    private Integer proficieny;
    private String certificationLink;
}
