package com.snva.springboot.bootcamp.model.bootcamp.livecode;

import com.snva.springboot.bootcamp.model.user.User;
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
public class ProblemStatement {
    @Id
    private String id;
    @DBRef
    private List<User> usersSubscribed;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String name;
    private String description;
    private Editorial editorial;
    private List<Solution> solutions;
    private List<Submission> submissions;
    private String sampleCode;
    private List<TestCase> testCases;
    private List <String>results;
    private List<Discussion> discussions;
    private List<String> similarQuestions;
    private List<String> relatedTopics;
    private List<String> tags;
}
