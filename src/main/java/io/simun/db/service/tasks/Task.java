package io.simun.db.service.tasks;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
@Getter
@Setter
@Builder
public class Task {

    @Id
    @Builder.Default
    private final UUID id = UUID.randomUUID();

    @Field
    private final String name;

    @Field
    private final String description;

    @Field
    private final State state;

    @Field
    private final Urgency urgency;
}
