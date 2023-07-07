package com.snva.springboot.bootcamp.controller.v1.api;


import com.snva.springboot.bootcamp.controller.v1.command.AdminSignupFormCommand;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.SessionRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.TechnologyRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.UpdateBootcampRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.livecode.ProblemStatementRequest;
import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.dto.model.bootcamp.TechnologyDto;
import com.snva.springboot.bootcamp.dto.model.bootcamp.livecode.ProblemStatementDto;
import com.snva.springboot.bootcamp.dto.model.user.UserDto;
import com.snva.springboot.bootcamp.exception.LearnerDromeException;
import com.snva.springboot.bootcamp.service.IBootcampService;
import com.snva.springboot.bootcamp.service.IProblemStatementService;
import com.snva.springboot.bootcamp.service.ISessionService;
import com.snva.springboot.bootcamp.service.ITechnologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/problemStatement")
@CrossOrigin(maxAge = 36000, origins = "*" , allowedHeaders = "*")
@Api(value = "bootcamp-application", description = "Operations pertaining to solving white boarded problems and compile them  on platform")


public class ProblemStatementController {
    private IProblemStatementService  problemStatementService;

    @GetMapping("/allProblemStatements")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllProblemStatements() {
        return ResponseEntity.ok(problemStatementService.allProblemStatement());
    }

    @GetMapping("/getProblemStatementById/{problemStatementId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getProblemStatementById( @PathVariable String problemStatementId) {
        return ResponseEntity.ok(problemStatementService.problemStatementById(problemStatementId));
    }

    @PostMapping("/addProblemStatement")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity addAllProblemStatements(@Valid ProblemStatementRequest problemStatementRequest) {
        return ResponseEntity.ok(problemStatementService.createProblemStatement(createProblemStatement(problemStatementRequest)));
    }

    private ProblemStatementDto createProblemStatement(ProblemStatementRequest problemStatementRequest) {
        return  null;
    }
    }


