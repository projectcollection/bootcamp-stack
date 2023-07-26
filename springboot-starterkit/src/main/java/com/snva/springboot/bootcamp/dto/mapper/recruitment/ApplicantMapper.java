package com.snva.springboot.bootcamp.dto.mapper.recruitment;

import com.snva.springboot.bootcamp.dto.mapper.SessionMapper;
import com.snva.springboot.bootcamp.dto.mapper.TechnologyStackMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;
import com.snva.springboot.bootcamp.model.recruitment.Applicant;

public class ApplicantMapper {

    public static ApplicantDto toApplicantDto(Applicant applicant) {
        return new ApplicantDto()
                .setId(applicant.getId())
                .setUniversity(applicant.getUniversity())
                .setSkills(applicant.getSkills())
                .setPhone(applicant.getPhone())
                .setEmail(applicant.getEmail())
                .setResumeLinks(applicant.getResumeLinks())
                .setDesignation(applicant.getDesignation())
                .setDegree(applicant.getDegree())
                .setTotalExp(applicant.getTotalExp())
                .setName(applicant.getName());
    }

    public static Applicant toApplicant(ApplicantDto applicant) {
        // List<Technology> getTechno = bootcamp.getTechnologyStack();
        return new Applicant()
                .setId(applicant.getId())
                .setUniversity(applicant.getUniversity())
                .setSkills(applicant.getSkills())
                .setPhone(applicant.getPhone())
                .setEmail(applicant.getEmail())
                .setResumeLinks(applicant.getResumeLinks())
                .setDesignation(applicant.getDesignation())
                .setDegree(applicant.getDegree())
                .setTotalExp(applicant.getTotalExp())
                .setName(applicant.getName());
    }

}
