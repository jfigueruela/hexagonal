package es.orquidea.hexagonal.rest.controller;


import es.orquidea.hexagonal.builder.UserDtoTestBuilder;
import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostUserControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private PostUserController postUserController;

    @Test
    public void test_createUser() {
        //Given
        User user = UserTestBuilder.builder().build().toUser();
        when(userMapper.toDomain(any())).thenReturn(user);
        when(userService.saveUser(any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        //When
        ResponseEntity<UserDto> responseEntity =
                postUserController.createUser(UserDtoTestBuilder.builder().build().toUserDto());

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(responseEntity.getBody().getClass(), UserDto.class);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());

    }
}
