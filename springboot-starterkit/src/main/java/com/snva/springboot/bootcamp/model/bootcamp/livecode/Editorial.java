package com.snva.springboot.bootcamp.model.bootcamp.livecode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@Getter
@Setter
public class Editorial {
    @NotNull(message = "{constraints.NotEmpty.message}")
    String editorialText;
    List<Comment> comments;
}
