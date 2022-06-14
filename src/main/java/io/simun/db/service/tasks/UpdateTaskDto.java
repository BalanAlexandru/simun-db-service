package io.simun.db.service.tasks;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateTaskDto {

    private UUID id;

    private String name;

    private String description;

    private State state;

    private Urgency urgency;
}
