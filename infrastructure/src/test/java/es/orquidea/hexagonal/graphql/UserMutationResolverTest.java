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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMutationResolverTest {
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserMutationResolver userMutationResolver;

    @Test
    void test_addUser() throws Exception {
        //Given
        User user = UserTestBuilder.builder().email(UserDtoTestBuilder.EMAIL_VALUE).build().toUser();
        when(userMapper.toDomain(any())).thenReturn(user);
        when(userService.saveUser(any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        //When
        UserDto userDto = userMutationResolver.addUser(UserDtoTestBuilder.NAME_VALUE,UserDtoTestBuilder.EMAIL_VALUE);
        //Then
        assertNotNull(userDto);
        assertNotNull(userDto.getId());
        assertEquals(user.getName(), UserDtoTestBuilder.NAME_VALUE);
        assertEquals(user.getEmail(), UserDtoTestBuilder.EMAIL_VALUE);

    }
}
