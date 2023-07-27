package com.snva.springboot.bootcamp.controller.v1.request.recruitment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Lazy;

import javax.validation.constraints.NotEmpty;
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
        private List<String> tags;
        private String mark;
        @Lazy
        private List<Object> university;
        private List<String> designation;
        private List<Object> degree;
        private List<String> skills;
        private List<String> resumeLinks;
        private List<Remark> remarks;

}

class Remark{
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String userId;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String Remark;
        private Date dateCreated;
        private  Date dateModified;
}
