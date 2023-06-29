package com.snva.springboot.bootcamp.dto.mapper;

import com.snva.springboot.bootcamp.dto.model.bootcamp.TechnologyDto;
import com.snva.springboot.bootcamp.controller.v1.request.bootcamp.TechnologyRequest;
import com.snva.springboot.bootcamp.model.bootcamp.Technology;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arpit Khandelwal.
 */
@Component
public class TechnologyStackMapper {

    public static TechnologyDto toTechnologyDto(Technology technology) {
        return new TechnologyDto()
                .setId(technology.getId())
                .setName(technology.getName())
                .setVendorName(technology.getVendorName())
                .setVersion(technology.getVersion());
    }

    public static List<TechnologyDto> toTechnologyStackDtoList(List<Technology> technologyStack) {
        List<TechnologyDto> technologyDtos = new ArrayList<>();
        technologyStack.stream().forEach(x -> technologyDtos.add(toTechnologyDto(x)));
        return technologyDtos;
    }

    public static List<Technology> toTechnologyStackList(List<TechnologyDto> technologyStack) {
        return null;
    }

    public static TechnologyDto toTechnologyDto(TechnologyRequest technologyRequest){
        return new TechnologyDto()
                .setId(technologyRequest.getId())
                .setName(technologyRequest.getName())
                .setVendorName(technologyRequest.getVendorName())
                .setVersion(technologyRequest.getVersion());
    }
    public static Technology toTechnology(TechnologyRequest technologyRequest){
        return new Technology()
                .setId(technologyRequest.getId())
                .setName(technologyRequest.getName())
                .setVendorName(technologyRequest.getVendorName())
                .setVersion(technologyRequest.getVersion());
    }
}
