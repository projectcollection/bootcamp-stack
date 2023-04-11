package com.starterkit.springboot.brs.service;

import com.starterkit.springboot.brs.controller.v1.request.bootcamp.UpdateBootcampRequest;
import com.starterkit.springboot.brs.dto.model.bootcamp.BootcampDto;
import com.starterkit.springboot.brs.exception.LearnerDromeException;
import com.starterkit.springboot.brs.model.bootcamp.Bootcamp;

import java.util.List;
import java.util.Optional;

public interface IBootcampService {


    List<BootcampDto> getAllBootcamps();
    BootcampDto getById(String id);
    List<BootcampDto> getByName(String name);
    List<BootcampDto> getAllBootcampsByUser(String id);
    boolean createBootcamp(BootcampDto bootcamp);
    boolean updateBootcamp(BootcampDto bootcampDto);
    boolean deleteBootcamp(String id);

    List<BootcampDto> registerUserBootcamp(BootcampDto email);

    Bootcamp updateUsersBootcamp(UpdateBootcampRequest bootcampRequest) throws LearnerDromeException;
}
