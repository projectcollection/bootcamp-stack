package com.snva.springboot.bootcamp.service;

import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.TechnologyRequest;
import com.snva.springboot.bootcamp.dto.mapper.TechnologyStackMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.TechnologyDto;
import com.snva.springboot.bootcamp.model.bootcamp.Technology;
import com.snva.springboot.bootcamp.repository.bootcamp.TechnologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService implements ITechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public List<TechnologyDto> getAllTech() {
        return TechnologyStackMapper.toTechnologyStackDtoList(technologyRepository.findAll());
    }

    @Override
    public Optional<TechnologyDto> getById(String id) {
        return Optional.ofNullable(TechnologyStackMapper.toTechnologyDto(technologyRepository.findById(id).get()));
    }

    @Override
    public List<TechnologyDto> getByName(String name) {
        return null;
    }

    @Override
    public List<TechnologyDto> getAllTechByUser(String email) {
        return null;
    }

    @Override
    public TechnologyDto update(TechnologyRequest technologyRequest) {
        return TechnologyStackMapper.toTechnologyDto(technologyRepository.save(TechnologyStackMapper.toTechnology(technologyRequest)));
    }

    @Override
    public TechnologyDto createTechStack(TechnologyDto technologyDto) {
        Technology technology = new Technology();
        technology
                .setName(technologyDto.getName())
                .setVersion(technologyDto.getVersion())
                .setVendorName(technologyDto.getVendorName());
        return TechnologyStackMapper.toTechnologyDto(technologyRepository.save(technology));
    }
}
