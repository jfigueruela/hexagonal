package es.orquidea.hexagonal.bootstrap;


import es.orquidea.hexagonal.db.mapper.UserEntityMapper;
import es.orquidea.hexagonal.db.repository.SpringDataUserRepository;
import es.orquidea.hexagonal.db.repository.UserDboRepository;
import es.orquidea.hexagonal.repository.UserRepository;
import es.orquidea.hexagonal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootServiceConfig {

    @Bean
    public UserRepository userRepository(SpringDataUserRepository springDataUserRepository, UserEntityMapper userMapper) {
        return new UserDboRepository(springDataUserRepository, userMapper);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}