package es.orquidea.hexagonal.bootstrap;


import es.orquidea.hexagonal.repository.UserRepository;
import es.orquidea.hexagonal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootServiceConfig {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserService(userRepository);
  }
}