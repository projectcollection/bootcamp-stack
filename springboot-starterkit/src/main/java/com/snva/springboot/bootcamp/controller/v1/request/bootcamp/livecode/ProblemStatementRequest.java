package com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode;

import lombok.Data;

import java.util.List;

@Data
public class ProblemStatementRequest {
    private String userId;
    private String languageId;
    private String problemStatementId;
    private String sampleCode;
    private String expectedOutput;
    private String stdin;
    private List<String> testCases;
}