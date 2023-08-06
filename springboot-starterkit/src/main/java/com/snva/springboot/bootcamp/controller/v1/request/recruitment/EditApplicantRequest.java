package com.snva.springboot.bootcamp.controller.v1.request.recruitment;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditApplicantRequest {
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String id;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String email;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String phone;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String name;
        private int totalExp;
        private List<Object> university;
        private List<String> designation;
        private List<Object> degree;
        private List<String> skills;
        private List<String> resumeLinks;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "{constraints.NotEmpty.message}")
        @Temporal(TemporalType.DATE)
        private Date dateOfContact;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String recruiterId;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "{constraints.NotEmpty.message}")
        @Temporal(TemporalType.DATE)
        private Date positionReceivingDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "{constraints.NotEmpty.message}")
        @Temporal(TemporalType.DATE)
        private Date submissionDate;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String positionTitle;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String candidateLocation;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String visaStatus;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private float payRate;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String candidateEmploymentType;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String submissionStatus;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String resumeSource;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private boolean willingRelocation;
        private List<Remark> remarks;
        private List<String> tags;
}


