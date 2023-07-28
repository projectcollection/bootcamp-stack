package com.snva.springboot.bootcamp.controller.v1.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.dto.model.bootcamp.TechnologyDto;
import com.snva.springboot.bootcamp.exception.LearnerDromeException;
import com.snva.springboot.bootcamp.service.IBootcampService;
import com.snva.springboot.bootcamp.service.ISessionService;
import com.snva.springboot.bootcamp.service.ITechnologyService;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.SessionRequest;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.TechnologyRequest;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.UpdateBootcampRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import javax.validation.constraints.NotNull;



@RestController
@RequestMapping("/api/v1/bootcamp")
@CrossOrigin(maxAge = 36000, origins = "*" , allowedHeaders = "*")
@Api(value = "brs-application", description = "Operations pertaining to agency management and ticket issue in the BRS application")
public class BootcampController {

    @Autowired
    IBootcampService bootcampService ;

    @Autowired
    ITechnologyService technologyService;

    @Autowired
    ISessionService sessionService;

    @GetMapping("/allbootcamps")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllBootcamps() {
        return ResponseEntity.ok(bootcampService.getAllBootcamps());
    }


    @RequestMapping(value ="/getbootcampbyid", method = RequestMethod.POST)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<?> getBootcampByIdn(@RequestBody @Valid RequestMes requestMes) {

        return ResponseEntity.ok(bootcampService.getById(requestMes.bootcampId));
    }


    // Technologies ------------ Start

    @GetMapping("/alltechesstacks")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllTechnologies() {
        return ResponseEntity.ok(technologyService.getAllTech());
    }

    @PostMapping("/techstackbyid/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllTechnologiesById(@PathVariable String id) {
        return ResponseEntity.ok(technologyService.getById(id));
    }

    @PostMapping("/createtechstack")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity createTechnologyStack(@RequestBody TechnologyDto technologyDto) {
        return ResponseEntity.ok(technologyService.createTechStack(technologyDto));
    }

    @PostMapping("/updatetechstack")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity updateTechnologyById(@Valid @RequestBody TechnologyRequest technologyRequest) {
        return ResponseEntity.ok(technologyService.update(technologyRequest));
    }

    // Technologies ------------ End
    // Sessions -------------- Start
    @GetMapping("/allsessions")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }


    @PostMapping("/sessionbyid")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity sessionById(@RequestBody String id) {
        return ResponseEntity.ok(sessionService.getById(id));
    }

    @PostMapping("/updatesession")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity updateSession(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sessionService.updateSession(sessionRequest));
    }

    @PostMapping("/createesession")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity createSession(@RequestBody SessionRequest sessionRequest) {
        return ResponseEntity.ok(sessionService.createSession(sessionRequest));
    }


    // Sessions --------------  End

    @PutMapping(value = "updatebootcamp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  ResponseEntity updateProfile(@RequestBody BootcampDto bootcampDto){
        return ResponseEntity.ok( bootcampService.updateBootcamp(bootcampDto));
    }

    @PutMapping(value = "adduserbootcamp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public  ResponseEntity addUserToBootcamp(@RequestBody UpdateBootcampRequest bootcampRequest) throws LearnerDromeException {
        return ResponseEntity.ok( bootcampService.updateUsersBootcamp(bootcampRequest));
    }


    @Getter
    @Setter
    @Accessors(chain = true)
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class RequestMes {
        @NotNull(message = "{constraints.NotEmpty.message}")
        private String bootcampId;
//        @NotNull(message = "{constraints.NotEmpty.message}")
//        private String password;
    }

}


