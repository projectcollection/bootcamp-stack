package com.snva.springboot.bootcamp.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Education {
    private  String name;
    private String university;
    private String city;
    private String state;
    private String country;
}
