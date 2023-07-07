package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.ProblemStatementRequest;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;

import java.util.List;
import java.util.Optional;

public class ProblemStatementService implements  IProblemStatementService {

    @Override
    public Optional<ProblemStatement> createProblemStatement(ProblemStatementDto problemStatementDto) {
        return Optional.empty();
    }

    @Override
    public Optional<ProblemStatement> updateProblemStatement() {
        return Optional.empty();
    }

    @Override
    public Optional<ProblemStatement> problemStatementById(String id) {
        return Optional.empty();
    }

    @Override
    public String DeleteProblemStatement() {
        return null;
    }

    @Override
    public List<ProblemStatement> allProblemStatement() {
        return null;
    }
}
