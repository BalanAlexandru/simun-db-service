package io.simun.db.service.tasks;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    TaskDto map(Task source);

    Task map(TaskDto source);

    Task mapForCreation(CreateTaskDto source);

    Task mapForUpdate(UpdateTaskDto source);
}
