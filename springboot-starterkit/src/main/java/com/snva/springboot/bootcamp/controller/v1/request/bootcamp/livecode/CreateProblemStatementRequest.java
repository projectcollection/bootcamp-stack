package com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Discussion;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Editorial;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Solution;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Submission;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateProblemStatementRequest {
    private  String id;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String description;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private Editorial editorial;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private List<Solution> solutions;
    private List<Submission> submissions;
    @NotNull(message = "{constraints.NotEmpty.message}")
    private String sampleCode;
    private List<String> testCases;
    private List <String> results;

    private List<String> similarQuestions;
    private List<String> relatedTopics;
    private List<String> tags;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
}
