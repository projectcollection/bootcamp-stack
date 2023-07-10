package com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode;

import com.snva.springboot.bootcamp.model.bootcamp.livecode.Discussion;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Editorial;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Solution;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.Submission;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class CreateProblemStatementRequest {
    private String name;
    private String description;
    private Editorial editorial;
    private List<Solution> solutions;
    private List<Submission> submissions;
    private String sampleCode;
    private List<String> testCases;
    private List <String>results;
    private List<Discussion> discussions;
    private List<String> similarQuestions;
    private List<String> relatedTopics;
    private List<String> tags;
}
