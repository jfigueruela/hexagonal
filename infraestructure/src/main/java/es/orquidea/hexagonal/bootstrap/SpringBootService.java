package es.orquidea.hexagonal.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "es.orquidea.hexagonal")
@EntityScan(basePackages = "es.orquidea.hexagonal")
public class SpringBootService {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootService.class, args);
    }

}