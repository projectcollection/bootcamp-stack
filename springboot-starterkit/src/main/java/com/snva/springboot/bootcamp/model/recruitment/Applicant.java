package com.snva.springboot.bootcamp.model.recruitment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "applicant")

public class Applicant {
    @Id
    String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
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
    private String phone;
    private String name;
    private int totalExp;
    private List<Object> university;
    private List<String> designation;
    private List<Object> degree;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private List<String> skills;
    private List<String> resumeLinks;
    private List<Remark> remarks;
    private List<String> tags;
    private String markStatus;
}