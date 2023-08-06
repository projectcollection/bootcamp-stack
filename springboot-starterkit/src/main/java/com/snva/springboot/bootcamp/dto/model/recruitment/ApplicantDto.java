package com.snva.springboot.bootcamp.dto.model.recruitment;

import com.snva.springboot.bootcamp.model.recruitment.Remark;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ApplicantDto {
    private String id;
    private String email;
    private String phone;
    private String name;
    private int totalExp;
    private List<Object> university;
    private List<String> designation;
    private List<Object> degree;
    private List<String> skills;
    private List<String> resumeLinks;
    private Date dateOfContact;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String recruiterId;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private Date positionReceivingDate;
    private Date submissionDate;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String positionTitle;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String candidateLocation;
    private String visaStatus;
    private  float payRate;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private  String  candidateEmploymentType;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private  String submissionStatus;
    private  String resumeSource;
    private  boolean willingRelocation;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private List<Remark> remarks;
    private List<String> tags;
    private String markStatus;


}



