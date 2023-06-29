package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.SessionRequest;
import com.snva.springboot.bootcamp.dto.model.bootcamp.SessionDto;

import java.util.List;
import java.util.Optional;

public interface ISessionService {

    List<SessionDto> getAllSessions();
    Optional<SessionDto> getById(String id);
    List<SessionDto> getByName(String name);
    List<SessionDto> getAllSessionsByUser(String email);

    SessionDto createSession(SessionRequest sessionRequest);

    SessionDto updateSession(SessionRequest sessionRequest);
}
