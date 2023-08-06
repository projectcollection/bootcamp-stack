package com.snva.springboot.bootcamp.dto.mapper.recruitment;

import com.snva.springboot.bootcamp.controller.v1.request.recruitment.EditApplicantRequest;
import com.snva.springboot.bootcamp.dto.mapper.SessionMapper;
import com.snva.springboot.bootcamp.dto.mapper.TechnologyStackMapper;
import com.snva.springboot.bootcamp.dto.model.bootcamp.BootcampDto;
import com.snva.springboot.bootcamp.dto.model.recruitment.ApplicantDto;
import com.snva.springboot.bootcamp.model.bootcamp.Bootcamp;
import com.snva.springboot.bootcamp.model.recruitment.Applicant;
import com.snva.springboot.bootcamp.model.recruitment.Remark;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicantMapper {

    public static ApplicantDto toApplicantDto(Applicant applicant) {
        List<Remark> remarkList= applicant.getRemarks();
//        remarkList.forEach(x->
//                {
//                    remarkList.add(new Remark().setRemark(x.getRemark()).setDateCreated(new Date()).setDateModified(new Date()).setUserId(applicant.getRecruiterId()));
//                }
//        );

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
                .setName(applicant.getName())
                .setPhone(applicant.getPhone())
                .setEmail(applicant.getEmail())
                .setDesignation(applicant.getDesignation())
                .setDegree(applicant.getDegree())
                .setName(applicant.getName())
                .setResumeLinks(applicant.getResumeLinks())
                .setWillingRelocation(applicant.isWillingRelocation())
                .setVisaStatus(applicant.getVisaStatus())
                .setTags(applicant.getTags())
                .setSubmissionStatus(applicant.getSubmissionStatus())
                .setSubmissionDate(applicant.getSubmissionDate())
                .setResumeSource(applicant.getResumeSource())
                .setRecruiterId(applicant.getRecruiterId())
                .setPositionTitle(applicant.getPositionTitle())
                .setPositionReceivingDate(applicant.getPositionReceivingDate())
                .setPayRate(applicant.getPayRate())
                .setDateOfContact(applicant.getDateOfContact())
                .setCandidateLocation(applicant.getCandidateLocation())
                .setCandidateEmploymentType(applicant.getCandidateEmploymentType())
                .setSkills(applicant.getSkills())
                .setUniversity(applicant.getUniversity())
                .setId(applicant.getId())
                .setTotalExp(applicant.getTotalExp())
                .setRemarks(remarkList);
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

    public  static  Applicant fromEditApplicanntRequestTpApplicant(EditApplicantRequest editApplicantRequest){

        List<Remark> remarks= new ArrayList<Remark>();
        List<com.snva.springboot.bootcamp.controller.v1.request.recruitment.Remark> remarkList= editApplicantRequest.getRemarks();
        remarkList.forEach(x->
                {
                    remarks.add(new Remark().setRemark(x.getRemark()).setDateCreated(new Date()).setDateModified(new Date()).setUserId(""));
                }
         );

        return  new Applicant()


                .setPhone(editApplicantRequest.getPhone())
                .setEmail(editApplicantRequest.getEmail())
                .setDesignation(editApplicantRequest.getDesignation())
                .setDegree(editApplicantRequest.getDegree())
                .setName(editApplicantRequest.getName())
                .setResumeLinks(editApplicantRequest.getResumeLinks())
                .setWillingRelocation(editApplicantRequest.isWillingRelocation())
                .setVisaStatus(editApplicantRequest.getVisaStatus())
                .setTags(editApplicantRequest.getTags())
                .setSubmissionStatus(editApplicantRequest.getSubmissionStatus())
                .setSubmissionDate(editApplicantRequest.getSubmissionDate())
                .setResumeSource(editApplicantRequest.getResumeSource())
                .setRecruiterId(editApplicantRequest.getRecruiterId())
                .setPositionTitle(editApplicantRequest.getPositionTitle())
                .setPositionReceivingDate(editApplicantRequest.getPositionReceivingDate())
                .setPayRate(editApplicantRequest.getPayRate())
                .setDateOfContact(editApplicantRequest.getDateOfContact())
                .setCandidateLocation(editApplicantRequest.getCandidateLocation())
                .setCandidateEmploymentType(editApplicantRequest.getCandidateEmploymentType())
                .setSkills(editApplicantRequest.getSkills())
                .setUniversity(editApplicantRequest.getUniversity())
                .setId(editApplicantRequest.getId())
                .setTotalExp(editApplicantRequest.getTotalExp())
                .setRemarks(remarks);
    }

}
