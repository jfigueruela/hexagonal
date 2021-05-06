package es.orquidea.hexagonal.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"es.orquidea.hexagonal.db.repository"})

public class SpringDataConfig {}