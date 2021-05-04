package es.orquidea.hexagonal.rest.controller;

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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private GetUserController getUserController;

    @Test
    void test_getUserById() {
        //Given
        when(userService.getUser(any())).thenReturn(UserTestBuilder.builder().build().toUser());
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        //When
        ResponseEntity<UserDto> responseEntity =
                getUserController.getUserById("test-id");

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(UserDto.class, responseEntity.getBody().getClass());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void test_getUsers() {
        //Given
        when(userService.getUsers()).thenReturn(Collections.singletonList(UserTestBuilder.builder().build().toUser()));
        when(userMapper.toDto(anyList())).thenReturn(Collections.singletonList(UserDtoTestBuilder.builder().build().toUserDto()));

        //When
        ResponseEntity<List<UserDto>> responseEntity =
                getUserController.getUsers();

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
