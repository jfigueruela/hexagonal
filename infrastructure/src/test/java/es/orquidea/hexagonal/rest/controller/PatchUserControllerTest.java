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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class PatchUserControllerTest {
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private PatchUserController patchUserController;

    @Test
     void test_updateUser() {
        //Given
        User user = UserTestBuilder.builder().build().toUser();
        when(userMapper.toDomain(any())).thenReturn(user);
        when(userService.updateUser(any(),any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        //When
        ResponseEntity<UserDto> responseEntity =
                patchUserController.updateUser(UserDtoTestBuilder.builder().build().toUserDto(), "test-id");

        //Then
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(UserDto.class,responseEntity.getBody().getClass());
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
