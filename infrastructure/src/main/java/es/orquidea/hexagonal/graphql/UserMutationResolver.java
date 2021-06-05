package es.orquidea.hexagonal.graphql;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserDto addUser(String name, String email) throws Exception {
        UserDto userDto = UserDto.builder().name(name).email(email).build();
        return userMapper.toDto(userService.saveUser(userMapper.toDomain(userDto)));
    }
}
