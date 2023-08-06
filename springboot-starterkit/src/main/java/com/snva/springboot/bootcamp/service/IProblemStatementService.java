package com.snva.springboot.bootcamp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CompileRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CreateProblemStatementRequest;
import com.snva.springboot.bootcamp.controller.v1.response.CompileResponse;
import com.snva.springboot.bootcamp.controller.v1.response.LanguagesResponse;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IProblemStatementService {

    Optional<ProblemStatement> createProblemStatement(CreateProblemStatementRequest problemStatementRequest);
    Optional<ProblemStatement> updateProblemStatement();
    Optional<ProblemStatement> problemStatementById(String id);
    String DeleteProblemStatement();
    List<ProblemStatement> allProblemStatement();

    CompileResponse compileProblemStatement(CompileRequest compileRequest);

    List<LanguagesResponse>  getAllLanguages() throws JsonProcessingException;
}
