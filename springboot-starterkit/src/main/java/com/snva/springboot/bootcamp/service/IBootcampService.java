package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.UpdateBootcampRequest;
import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.exception.LearnerDromeException;
import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;

import java.util.List;

public interface IBootcampService {


    List<BootcampDto> getAllBootcamps();
    BootcampDto getById(String id);
    List<BootcampDto> getByName(String name);
    List<BootcampDto> getAllBootcampsByUser(String email);
    boolean createBootcamp(BootcampDto bootcamp);
    boolean updateBootcamp(BootcampDto bootcampDto);
    boolean deleteBootcamp(String id);

    List<BootcampDto> registerUserBootcamp(BootcampDto email);

    Bootcamp updateUsersBootcamp(UpdateBootcampRequest bootcampRequest) throws LearnerDromeException;
}
