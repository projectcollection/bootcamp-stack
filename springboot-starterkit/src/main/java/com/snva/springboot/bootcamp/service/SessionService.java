package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.SessionRequest;
import com.snva.springboot.bootcamp.dto.mapper.SessionMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.SessionDto;
import com.snva.springboot.bootcamp.repository.bootcamp.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService implements ISessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<SessionDto> getAllSessions() {
        return SessionMapper.toSessionDtoList(sessionRepository.findAll());
    }

    @Override
    public Optional<SessionDto> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<SessionDto> getByName(String name) {
        return null;
    }

    @Override
    public List<SessionDto> getAllSessionsByUser(String email) {
        return null;
    }

    @Override
    public SessionDto createSession(SessionRequest sessionRequest) {
        return SessionMapper.toSessionDto( sessionRepository.save(SessionMapper.toSession(sessionRequest)));
    }

    @Override
    public SessionDto updateSession(SessionRequest sessionRequest) {
        return SessionMapper.toSessionDto( sessionRepository.save(SessionMapper.toSession(sessionRequest)));
    }
}
