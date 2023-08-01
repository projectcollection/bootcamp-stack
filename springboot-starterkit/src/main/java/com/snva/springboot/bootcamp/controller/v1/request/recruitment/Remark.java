package com.snva.springboot.bootcamp.controller.v1.request.recruitment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Remark{
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String userId;
        @NotEmpty(message = "{constraints.NotEmpty.message}")
        private String Remark;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "{constraints.NotEmpty.message}")
        @Temporal(TemporalType.DATE)
        private Date dateCreated;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        @NotNull(message = "{constraints.NotEmpty.message}")
        @Temporal(TemporalType.DATE)
        private  Date dateModified;
}