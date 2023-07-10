package com.snva.springboot.bootcamp.model.bootcamp;

import com.snva.springboot.bootcamp.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "codeCompile")
public class CodeCompile {
    @Id
    String id;
    String response;
    @Embedded
    User user;
    Date runDate;
}
