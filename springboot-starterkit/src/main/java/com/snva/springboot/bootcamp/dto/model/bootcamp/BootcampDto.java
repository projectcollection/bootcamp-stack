package com.snva.springboot.bootcamp.dto.model.bootcamp;

import com.snva.springboot.bootcamp.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BootcampDto {
    String id;
    String name;
    Date startSate;
    Date endDate;
    String bannerSmallImage;
    String bannerLargeImage;
    String bannerVideoLink;
    String description;
    String longHtml;
    List<User> users;
    List<TechnologyDto> technologyStack;
    List <SessionDto> sessions;
}
