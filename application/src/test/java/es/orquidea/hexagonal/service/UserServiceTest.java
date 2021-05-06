package es.orquidea.hexagonal.service;

import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

//    @Disabled
//    @Test
//    public void test_getUser() {
//        //Given
//        when(userRepository.findById(any())).thenReturn(UserTestBuilder.builder().build().toUser());
//
//        //When
//        User user = userService.getUser("test-id");
//
//        //Then
//        verify(userRepository,times(1)).findById(any());
//        assertNotNull(user);
//    }
//
//    @Disabled
//    @Test
//    public void test_getUser_throwsException() {
//        //Give
//        when(userRepository.findById(any())).thenReturn(null);
//
//        //When - Then
//        assertThrows(ResourceNotFoundException.class,
//                () -> userService.getUser("test-id"));
//    }
//
//    @Disabled
//    @Test
//    public void test_getUsers() {
//        //Given
//        when(userRepository.getAll()).thenReturn(Collections.singletonList(UserTestBuilder.builder().build().toUser()));
//
//        //When
//        List<User> users = userService.getUsers();
//
//        assertNotNull(users);
//        assertEquals(1,users.size());
//        verify(userRepository,times(1)).getAll();
//    }
//
//    @Disabled
//    @Test
//    public void test_saveUser() {
//        //Given
//        when(userRepository.save(any())).thenReturn(UserTestBuilder.builder().build().toUser());
//
//        //When
//        User user = userService.saveUser(UserTestBuilder.builder().build().toUser());
//
//        //Then
//        assertNotNull(user);
//        verify(userRepository,times(1)).save(any());
//    }
//
//    @Disabled
//    @Test
//    public void test_delete() {
//        //Given
//        when(userRepository.findById(any())).thenReturn(UserTestBuilder.builder().build().toUser());
//        doNothing().when(userRepository).delete(any());
//
//        //When
//        userService.deleteUser("test-id");
//
//        //Then
//        verify(userRepository,times(1)).delete(any());
//    }
//
//    @Disabled
//    @Test
//    public void test_updateUser() {
//        //Given
//        when(userRepository.save(any())).thenReturn(UserTestBuilder.builder().build().toUser());
//
//        //When
//        User user = userService.updateUser("test-id", UserTestBuilder.builder().build().toUser());
//
//        //Then
//        assertNotNull(user);
//        verify(userRepository,times(1)).save(any());
//    }
}
