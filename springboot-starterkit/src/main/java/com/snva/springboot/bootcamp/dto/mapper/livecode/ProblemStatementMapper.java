package com.snva.springboot.bootcamp.dto.mapper.livecode;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CreateProblemStatementRequest;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;
import org.springframework.stereotype.Component;

/**
 * Created by Dheeraj Singh
 */
@Component
public class ProblemStatementMapper {
    public static CreateProblemStatementRequest toUserDto(ProblemStatement problemStatement) {
        return new CreateProblemStatementRequest()
                .setId(problemStatement.getId())
                .setDescription(problemStatement.getDescription())
//                .setDiscussions(problemStatement.getDiscussions())
                .setEditorial(problemStatement.getEditorial())
                .setName(problemStatement.getName())
                .setResults(problemStatement.getResults())
                .setRelatedTopics(problemStatement.getRelatedTopics())
                .setSampleCode(problemStatement.getSampleCode())
                .setSolutions(problemStatement.getSolutions())
                .setSimilarQuestions(problemStatement.getSimilarQuestions())
                .setSubmissions(problemStatement.getSubmissions())
                .setTestCases(problemStatement.getTestCases())
                .setTags(problemStatement.getTags());
    }
    public static ProblemStatement toUser(CreateProblemStatementRequest problemStatementRequest) {
        return new ProblemStatement()
                .setId(problemStatementRequest.getId())
                .setDescription(problemStatementRequest.getDescription())
//                .setDiscussions(problemStatementRequest.getDiscussions())
                .setEditorial(problemStatementRequest.getEditorial())
                .setName(problemStatementRequest.getName())
                .setResults(problemStatementRequest.getResults())
                .setRelatedTopics(problemStatementRequest.getRelatedTopics())
                .setSampleCode(problemStatementRequest.getSampleCode())
                .setSolutions(problemStatementRequest.getSolutions())
                .setSimilarQuestions(problemStatementRequest.getSimilarQuestions())
                .setSubmissions(problemStatementRequest.getSubmissions())
                .setTestCases(problemStatementRequest.getTestCases())
                .setTags(problemStatementRequest.getTags());
    }
}
