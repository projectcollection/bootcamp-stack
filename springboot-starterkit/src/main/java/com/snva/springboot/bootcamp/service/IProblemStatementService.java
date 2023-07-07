package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.ProblemStatementRequest;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;

import java.util.List;
import java.util.Optional;

public interface IProblemStatementService {

    Optional<ProblemStatement> createProblemStatement(ProblemStatementDto problemStatementRequest);
    Optional<ProblemStatement> updateProblemStatement();
    Optional<ProblemStatement> problemStatementById(String id);
    String DeleteProblemStatement();
    List<ProblemStatement> allProblemStatement();

}
