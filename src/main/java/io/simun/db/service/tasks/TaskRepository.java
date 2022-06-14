package io.simun.db.service.tasks;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends ReactiveMongoRepository<Task, UUID> {

}
