package io.simun.db.service.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public Mono<TaskDto> createTask(CreateTaskDto createTaskDto) {
        return taskRepository
                .save(taskMapper.mapForCreation(createTaskDto))
                .doOnNext(task -> log.info("New task created with id: {}", task.getId()))
                .map(taskMapper::map);
    }

    public Mono<TaskDto> getTask(UUID taskId) {
        return taskRepository.findById(taskId)
                .map(taskMapper::map);
    }

    public Flux<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .map(taskMapper::map);
    }

    public Mono<TaskDto> updateTask(UpdateTaskDto updateTaskDto) {
        return getTask(updateTaskDto.getId())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + updateTaskDto.getId())))
                .flatMap(taskDto -> taskRepository
                        .save(taskMapper.mapForUpdate(updateTaskDto))
                        .doOnNext(task -> log.info("Task with id {} has been updated", task.getId()))
                        .map(taskMapper::map));
    }

    public Mono<Void> deleteTask(UUID taskId) {
        return getTask(taskId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + taskId)))
                .flatMap(taskDto -> taskRepository
                        .deleteById(taskId)
                        .doOnSuccess(v -> log.info("Task with id {} has been deleted", taskId)));
    }
}
