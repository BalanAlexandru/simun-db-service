package io.simun.db.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SimunDbServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimunDbServiceApplication.class, args);
    }

}
