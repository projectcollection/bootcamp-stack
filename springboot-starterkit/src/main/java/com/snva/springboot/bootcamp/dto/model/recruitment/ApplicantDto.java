package com.snva.springboot.bootcamp.dto.model.recruitment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplicantDto {
    private String id;
    private String email;
    private String phone;
    private String name;
    private int totalExp;
    private List<Object> university;
    private List<String> designation;
    private List<Object> degree;
    private List<String> skills;
    private List<String> resumeLinks;

}
