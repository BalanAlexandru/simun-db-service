package io.simun.db.service.tasks;

import lombok.*;

@Data
@Builder
public class CreateTaskDto {

    private String name;

    private String description;

    private State state;

    private Urgency urgency;
}
