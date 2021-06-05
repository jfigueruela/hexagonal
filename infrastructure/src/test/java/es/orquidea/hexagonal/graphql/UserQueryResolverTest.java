package es.orquidea.hexagonal.graphql;

import es.orquidea.hexagonal.builder.UserDtoTestBuilder;
import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueryResolverTest {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserQueryResolver userQueryResolver;

    @Test
    void test_getUserById() throws Exception {
        //Given
        User user = UserTestBuilder.builder().email(UserDtoTestBuilder.EMAIL_VALUE).build().toUser();
        when(userService.getUser(any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        //When
        UserDto userDto =
                userQueryResolver.getUserById("test-id");

        //Then
        assertNotNull(userDto);
        assertNotNull(userDto.getId());
        assertEquals(userDto.getName(), UserDtoTestBuilder.NAME_VALUE);
        assertEquals(userDto.getEmail(), UserDtoTestBuilder.EMAIL_VALUE);
    }
}
