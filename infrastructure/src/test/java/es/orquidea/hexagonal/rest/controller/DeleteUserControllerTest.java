package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DeleteUserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private DeleteUserController deleteUserController;

    @Test
     void test_deleteUserById(){
        //Given
        doNothing().when(userService).deleteUser(any());

        //When
        ResponseEntity<?> responseEntity = deleteUserController.deleteUserById("mock-id");

        //Then
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        verify(userService,times(1)).deleteUser(any());
    }

}
