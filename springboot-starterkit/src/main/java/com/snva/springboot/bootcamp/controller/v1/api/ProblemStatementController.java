package com.snva.springboot.bootcamp.controller.v1.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CompileRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.CreateProblemStatementRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.ProblemStatementRequest;
import com.snva.springboot.bootcamp.dto.mapper.UserMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.model.user.User;
import com.snva.springboot.bootcamp.service.IProblemStatementService;
import com.snva.springboot.bootcamp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/problemStatement")
@CrossOrigin(maxAge = 36000, origins = "*" , allowedHeaders = "*")
@Api(value = "bootcamp-application", description = "Operations pertaining to solving white boarded problems and compile them  on platform")
public class ProblemStatementController {
    private static Logger logger = LoggerFactory.getLogger(ProblemStatementController.class);
    @Autowired
    private IProblemStatementService problemStatementService;

    @Autowired
    private UserService userService;

    @GetMapping("/allProblemStatements")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllProblemStatements() {
        return ResponseEntity.ok(problemStatementService.allProblemStatement());
    }

    @GetMapping("/getProblemStatementById/{problemStatementId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getProblemStatementById(@PathVariable String problemStatementId) {
        return ResponseEntity.ok(problemStatementService.problemStatementById(problemStatementId));
    }

    @PostMapping("/addProblemStatement")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity addProblemStatement(@Valid @RequestBody CreateProblemStatementRequest problemStatementRequest) {
        return ResponseEntity.ok(problemStatementService.createProblemStatement(problemStatementRequest));
    }


    @PostMapping(value = "/compile")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity compile(@Valid @RequestBody ProblemStatementRequest problemStatementRequest) {
        CompileRequest compileRequest = new CompileRequest();
        compileRequest.setLanguageId(problemStatementRequest.getLanguageId());
        compileRequest.setExpectedOutput(problemStatementRequest.getExpectedOutput());
        compileRequest.setSourceCode(problemStatementRequest.getSampleCode());
        compileRequest.setStdin(problemStatementRequest.getStdin());
        compileRequest.setAdditionalProperty("userId",problemStatementRequest.getUserId());
        return ResponseEntity.ok(problemStatementService.compileProblemStatement(compileRequest));
    }


    @GetMapping(value = "/languages")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllLanguageMap() {
        try {
            return ResponseEntity.ok(problemStatementService.getAllLanguages());
        } catch (JsonProcessingException e) {
            return ResponseEntity.ok(new String("No Data")+e.getMessage());
        }
    }


}

