package io.simun.db.service.tasks;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TaskDto> createTask(@RequestBody CreateTaskDto createTaskDto) {
        return taskService.createTask(createTaskDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<TaskDto> getTask(@PathVariable UUID id) {
        var response = taskService.getTask(id);

        return response.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No task found with id: " + id)));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<TaskDto> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<TaskDto> updateTask(@RequestBody UpdateTaskDto updateTaskDto) {
        return taskService.updateTask(updateTaskDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable UUID id) {
        return taskService.deleteTask(id);
    }
}
