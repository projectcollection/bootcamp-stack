package com.snva.springboot.bootcamp.controller.v1.request.bootcamp;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class UpdateBootcampRequest {

    String id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    Date startSate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{constraints.NotEmpty.message}")
    @Temporal(TemporalType.DATE)
    Date endDate;
    @NotNull(message = "{constraints.NotEmpty.message}")
    String description;
    @NotNull(message = "{constraints.NotEmpty.message}")
    String longHtml;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    String []userIds; // ["fsdfsdfdsfsd",."fsdfsdfsdfsd"]
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    String [] technologyStackIds;
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    String [] sessionIds;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String bannerSmallImage;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String bannerLargeImage;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    String bannerVideoLink;


}
