package com.snva.springboot.bootcamp.model.bootcamp.livecode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class Editorial {
    String editorialText;
    List<Comment> comments;
}
