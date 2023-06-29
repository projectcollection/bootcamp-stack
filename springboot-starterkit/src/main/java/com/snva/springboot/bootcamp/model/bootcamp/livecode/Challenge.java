package com.snva.springboot.bootcamp.model.bootcamp.livecode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "challenge")
public class Challenge {
    @Id
    String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    String name;
    String description;
    Editorial editorial;
    @DBRef
    List<Solution> solutions;
    @DBRef
    List<Submission> submissions;
    String sampleCode;
    @DBRef
    List<TestCase> testCases;
    String result;
}
