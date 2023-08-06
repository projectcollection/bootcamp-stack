package com.snva.springboot.bootcamp.model.bootcamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "technology")
public class Technology {
    @Id
    String id;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    String name;
    String vendorName;
    String version;
}
