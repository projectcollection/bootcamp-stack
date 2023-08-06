package com.snva.springboot.bootcamp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snva.springboot.bootcamp.controller.v1.request.recruitment.EditApplicantRequest;
import com.snva.springboot.bootcamp.controller.v1.response.ml.api.ResumeParsingResponse;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IResumeParsingService {

    ResumeParsingResponse getResumeParsed(Resource file) throws JsonProcessingException;
    ApplicantDto addApplicant(ApplicantDto file);

    List<ApplicantDto> allApplicants();
    List<ApplicantDto> allApplicants(String id);


    ApplicantDto applicantById(String id);

    ApplicantDto updateApplicant(EditApplicantRequest editApplicantRequest);
//    List<ResumeParsingResponse> addApplicant(String booleanSearch);

}
