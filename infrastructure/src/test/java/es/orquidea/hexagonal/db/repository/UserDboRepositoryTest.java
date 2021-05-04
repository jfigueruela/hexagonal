package es.orquidea.hexagonal.db.repository;

import es.orquidea.hexagonal.builder.UserEntityTestBuilder;
import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.db.dbo.UserEntity;
import es.orquidea.hexagonal.db.mapper.UserEntityMapper;
import es.orquidea.hexagonal.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDboRepositoryTest {
    @Mock
    private SpringDataUserRepository springDataUserRepository;
    @Mock
    private UserEntityMapper userEntityMapper;
    @InjectMocks
    private UserDboRepository userDboRepository;

    @Test
    public void test_findById() {
        //Given
        when(springDataUserRepository.findById(anyString())).thenReturn(UserEntityTestBuilder.builder().build().toOptionaUserEntity());
        when(userEntityMapper.toDomain(any(UserEntity.class))).thenReturn(UserTestBuilder.builder().build().toUser());

        //When
        User user = userDboRepository.findById("test-id");

        //Then
        verify(springDataUserRepository,times(1)).findById(anyString());
        assertNotNull(user);
    }

    @Test
    public void test_getAll() {
        when(springDataUserRepository.findAll()).thenReturn(Collections.singletonList(UserEntityTestBuilder.builder().build().toUserEntity()));
        when(userEntityMapper.toDomain(anyList())).thenReturn(Collections.singletonList(UserTestBuilder.builder().build().toUser()));

        //When
        List<User> users = userDboRepository.getAll();

        //Then
        verify(springDataUserRepository,times(1)).findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void test_save() {
        //Given
        when(userEntityMapper.toDbo(any())).thenReturn(UserEntityTestBuilder.builder().build().toUserEntity());
        when(springDataUserRepository.save(any())).thenReturn(UserEntityTestBuilder.builder().build().toUserEntity());

        //When
        User user = userDboRepository.save(UserTestBuilder.builder().build().toUser());

        //Then
        verify(springDataUserRepository,times(1)).save(any());
    }

    @Test
    public void test_delete() {
        //Given
        doNothing().when(springDataUserRepository).delete(any());
        //When

        userDboRepository.delete(UserTestBuilder.builder().build().toUser());

        //Then
        verify(springDataUserRepository,times(1)).delete(any());
    }
}
