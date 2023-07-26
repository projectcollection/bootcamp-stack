package com.snva.springboot.bootcamp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snva.springboot.bootcamp.controller.v1.response.ml.api.Data;
import com.snva.springboot.bootcamp.controller.v1.response.ml.api.ResumeParsingResponse;
import com.snva.springboot.bootcamp.dto.mapper.recruitment.ApplicantMapper;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import com.snva.springboot.bootcamp.model.recruitment.Applicant;
import com.snva.springboot.bootcamp.repository.recruitment.ApplicantRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ResumeServiceImpl implements  IResumeParsingService {
    @Value("${spring.app.ai.python.resume.url}")
    private  String AI_RESUME_PARSER;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public ResumeParsingResponse getResumeParsed(Resource file) throws JsonProcessingException {
        ResponseEntity<String> response = null;
        String fileName = "";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file",file);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForEntity(AI_RESUME_PARSER, requestEntity, String.class);
            return getFromJson(response.getBody());
    }

    @Override
    public ApplicantDto addApplicant(ApplicantDto applicantDto) {
        Optional<Applicant> applicantExist = applicantRepository.findByPhone(applicantDto.getPhone());
        Applicant applicant = applicantExist.isPresent()? mapper.map(applicantExist.get(), Applicant.class):             applicantRepository.findByPhone(applicantDto.getPhone()).isPresent()?  mapper.map(  applicantRepository.findByPhone(applicantDto.getPhone()).get(), Applicant.class):null;
        if (applicant==null) {
            applicant = new Applicant();
            applicant = mapper.map(applicantDto, Applicant.class);
            applicant.setPhone(
                    applicantDto.getPhone() == null ? "NA" : applicantDto.getPhone() == "" ? "NA" : applicantDto.getPhone()
            );
            applicant.setEmail(
                    applicantDto.getEmail() == null ? "NA" : applicantDto.getEmail() == "" ? "NA" : applicantDto.getEmail()
            );
            applicant.setName(
                    applicantDto.getName() == null ? "NA" : applicantDto.getName() == "" ? "NA" : applicantDto.getName()
            );
        }
        List<String> resumeLinkList = applicant.getResumeLinks();
        applicant.setResumeLinks(resumeLinkList);
        return ApplicantMapper.toApplicantDto(applicantRepository.save(applicant));
    }

    @Override
    public List<ApplicantDto> allApplicants() {
        List<Applicant> applicants = applicantRepository.findAll();
        List<ApplicantDto> applicantDtos =new ArrayList<>();
        if (applicants.size()>0){
            applicants.forEach(x-> applicantDtos.add(ApplicantMapper.toApplicantDto(x)) );
        }
        return  applicantDtos;
    }

    private ResumeParsingResponse getFromJson(String body) throws JsonProcessingException {
        ResumeParsingResponse resumeParsingResponse = new ResumeParsingResponse();
        JSONObject jsonObject = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        jsonObject = new JSONObject(body);
        resumeParsingResponse.setData(mapper.readValue(jsonObject.getString("data"), Data.class));
        resumeParsingResponse.setStatusCode(jsonObject.getInt("status_code"));
        resumeParsingResponse.setMessage(jsonObject.getString("message"));
        return resumeParsingResponse;
    }
}
