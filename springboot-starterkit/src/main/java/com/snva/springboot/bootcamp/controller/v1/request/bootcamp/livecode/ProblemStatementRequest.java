package com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProblemStatementRequest {
    private String userId;
    private String languageId;
    private String problemStatementId;
    private String sampleCode;
    private String expectedOutput;
    private String stdin;
    private List<String> testCases;
}