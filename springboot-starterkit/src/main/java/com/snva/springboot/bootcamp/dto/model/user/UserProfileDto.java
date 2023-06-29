package com.snva.springboot.bootcamp.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.snva.springboot.bootcamp.model.user.Education;
import com.snva.springboot.bootcamp.model.user.Experience;
import com.snva.springboot.bootcamp.model.user.Project;
import com.snva.springboot.bootcamp.model.user.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDto {
    private  String id;
    private  String name;
    private String linkedIn;
    private String github;
    private  String stackOverflow;
    private Set<Experience> experience;
    private Set<Education> education;
    private Set<Skill> skills;
    private  Set<Project> projects;
}
