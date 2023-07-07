package com.snva.springboot.bootcamp.service;

import com.google.gson.Gson;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CompileRequest;
import com.snva.springboot.bootcamp.controller.v1.response.CompileResponse;
import com.snva.springboot.bootcamp.controller.v1.response.SubmissionResponse;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class ProblemStatementService implements  IProblemStatementService {


    @Autowired
    RestTemplate restTemplate;

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

    @Override
    public CompileResponse compileProblemStatement(CompileRequest problemStatementDto) {
        CompileResponse compileResponse = new CompileResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-RapidAPI-Key", "f2d45fb067msh15433c11db98ab6p10f2f8jsn698b502b0f41");
        headers.add("X-RapidAPI-Host", "judge0-ce.p.rapidapi.com");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("language_id", problemStatementDto.getLanguageId());
        map.put("source_code", problemStatementDto.getSourceCode());
        map.put("stdin", problemStatementDto.getStdin());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity("https://judge0-ce.p.rapidapi.com/submissions?base64_encoded=true&fields=*", entity, String.class);
            SubmissionResponse submissionResponse = new Gson().fromJson(response.getBody(), SubmissionResponse.class);
            try {
                if (response.getStatusCode() == HttpStatus.CREATED) {
                    System.out.println("JSONISED RESPONSE" + response.getBody());
                    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
                    ResponseEntity<String> responseString= restTemplate.exchange("https://judge0-ce.p.rapidapi.com/submissions/" + submissionResponse.getToken() + "?base64_encoded=false&fields=*",HttpMethod.GET, requestEntity,String.class);
                    if (responseString.getStatusCode() == HttpStatus.OK) {
                        compileResponse = new Gson().fromJson(responseString.getBody(),CompileResponse.class);
                    }
                } else {
                    System.out.println("Request Failed");
                    System.out.println(response.getStatusCode());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return compileResponse;
    }
}
