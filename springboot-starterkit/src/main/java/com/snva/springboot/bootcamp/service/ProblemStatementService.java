package com.snva.springboot.bootcamp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CompileRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CreateProblemStatementRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.Status;
import com.snva.springboot.bootcamp.controller.v1.response.CompileResponse;
import com.snva.springboot.bootcamp.controller.v1.response.LanguagesResponse;
import com.snva.springboot.bootcamp.controller.v1.response.SubmissionResponse;
import com.snva.springboot.bootcamp.dto.mapper.UserMapper;
import com.snva.springboot.bootcamp.dto.mapper.livecode.ProblemStatementMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.model.bootcamp.CodeCompile;
import com.snva.springboot.bootcamp.model.bootcamp.livecode.ProblemStatement;

import com.snva.springboot.bootcamp.model.user.User;

import com.snva.springboot.bootcamp.repository.livecode.CodeCompileRepository;
import com.snva.springboot.bootcamp.repository.livecode.ProblemStatementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class ProblemStatementService implements  IProblemStatementService {
    private static Logger logger = LoggerFactory.getLogger(ProblemStatementService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserService userService;

    @Autowired
    CodeCompileRepository compilationRepository;

    @Autowired
    ProblemStatementRepository problemStatementRepository;

    @Override
    public Optional<ProblemStatement> createProblemStatement(CreateProblemStatementRequest problemStatementDto) {
        ProblemStatement problemStatement = new ProblemStatement();


        problemStatementRepository.save(ProblemStatementMapper.toUser(problemStatementDto));
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
        return problemStatementRepository.findAll();
    }

    @Override

    public CompileResponse compileProblemStatement(CompileRequest compileRequest) {
        User userToSave =new User();
        CompileResponse compileResponse = new CompileResponse();
        CodeCompile codeCompile = new CodeCompile();
        Map<String,Object> objectMap= compileRequest.getAdditionalProperties();
        userToSave= objectMap.size()>0? UserMapper.toUser(userService.findUserById(String.valueOf(objectMap.get("userId")))):  UserMapper.toUser(userService.findUserByEmail("dheeraj.singh@snva.com"));
        HttpHeaders headers = getHeaders();
        Map<String, Object> map = new HashMap<>();
        map.put("language_id", compileRequest.getLanguageId());
        map.put("source_code", compileRequest.getSourceCode());
        map.put("stdin", compileRequest.getStdin());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity("http://localhost:2358/submissions?base64_encoded=true&fields=*", entity, String.class);
            SubmissionResponse submissionResponse = new Gson().fromJson(response.getBody(), SubmissionResponse.class);
            try {
                if (response.getStatusCode() == HttpStatus.CREATED) {
                    logger.info("Info Message FromCreate Submissions", response);
                    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
                    codeCompile.setResponse(response.getBody()+"\n");
                    Thread.sleep(6000);
                    ResponseEntity<String> responseString = restTemplate.exchange("http://localhost:2358/submissions/" + submissionResponse.getToken() + "?base64_encoded=true&fields=*", HttpMethod.GET, requestEntity, String.class);
                    if (responseString.getStatusCode() == HttpStatus.OK) {
                        compileResponse = new Gson().fromJson(responseString.getBody(), CompileResponse.class);
                        codeCompile.setResponse(codeCompile.getResponse() + responseString.getBody());
                        codeCompile.setUser(userToSave);
                        codeCompile.setRunDate(new Date());

                    }
                } else {
                    logger.info("Info Message From Submissions", response);
                    Status status = new Status();
                    status.setDescription(response.getBody());
                    status.setId(response.getStatusCode().value());
                    compileResponse.setStatus(status);
                    codeCompile.setResponse(codeCompile.getResponse() + response.getBody());
                    codeCompile.setUser(userToSave);
                    codeCompile.setRunDate(new Date());

                }
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
                Status status = new Status();
                status.setDescription(e.getMessage());
                status.setId(400);
                compileResponse.setStatus(status);
                codeCompile.setResponse(e.getMessage());
                codeCompile.setUser(userToSave);
                codeCompile.setRunDate(new Date());

            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            Status status = new Status();
            status.setDescription(e.getMessage());
            status.setId(400);
            compileResponse.setStatus(status);
            codeCompile.setResponse(e.getMessage());
           codeCompile.setUser(userToSave);
            codeCompile.setRunDate(new Date());

        }
        compilationRepository.save(codeCompile);
        return compileResponse;
    }

    private HttpHeaders getHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-RapidAPI-Key", "f2d45fb067msh15433c11db98ab6p10f2f8jsn698b502b0f41");
        headers.add("X-RapidAPI-Host", "http://localhost:2358");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    @Override
    public   List<LanguagesResponse> getAllLanguages() throws JsonProcessingException {
        Map<String, Object> mapping = new HashMap<>();
        ResponseEntity<String> responseString = restTemplate.exchange("http://localhost:2358/languages", HttpMethod.GET, new HttpEntity<>(getHeaders()), String.class);
        return new ObjectMapper().readValue(responseString.getBody(), List.class);
    }
}
