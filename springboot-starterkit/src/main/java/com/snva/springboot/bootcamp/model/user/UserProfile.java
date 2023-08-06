package com.snva.springboot.bootcamp.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Accessors(chain = true)
@Document(collection = "userProfile")
public class UserProfile {
    @Id
    private  String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private  String name;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String linkedIn;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String github;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private  String stackOverflow;
    @Embedded
    private Set<Experience> experience;
    @Embedded
    private Set<Education> education;
    @Embedded
    private Set<Skill> skills;
    @Embedded
    private  Set<Project> projects;


    public UserProfile(String name){
        this.skills= new HashSet<>();
        this.education= new HashSet<>();
        this.projects= new HashSet<>();
        this.experience= new HashSet<>();
        this.stackOverflow = "";
        this.linkedIn="";
        this.github="";
        this.name=name;
    }

    public UserProfile(){
        this.skills= new HashSet<>();
        this.education= new HashSet<>();
        this.projects= new HashSet<>();
        this.experience= new HashSet<>();
        this.stackOverflow = "";
        this.linkedIn="";
        this.github="";
        this.name="";

    }

}
