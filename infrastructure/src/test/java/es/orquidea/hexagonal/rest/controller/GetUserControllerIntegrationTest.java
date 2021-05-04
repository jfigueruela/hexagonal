package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.builder.UserDtoTestBuilder;
import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = GetUserController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class GetUserControllerIntegrationTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void perform_get_user_by_id_200() throws Exception {
        when(userService.getUser(any())).thenReturn(UserTestBuilder.builder().build().toUser());
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        mvc.perform(MockMvcRequestBuilders.get("/" + GetUserController.GET_USER_BY_ID_URL, "test-id-path-param")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$." + UserDto.ID_FIELD).exists())
                .andExpect(jsonPath("$." + UserDto.ID_FIELD).value(UserDtoTestBuilder.ID_VALUE))
                .andExpect(jsonPath("$." + UserDto.NAME_FIELD).value(UserDtoTestBuilder.NAME_VALUE))
                .andExpect(jsonPath("$." + UserDto.EMAIL_FIELD).value(UserDtoTestBuilder.EMAIL_VALUE));
    }

    @Test
    void perform_get_users_200() throws Exception {
        when(userService.getUsers()).thenReturn(Collections.singletonList(UserTestBuilder.builder().build().toUser()));
        when(userMapper.toDto(anyList())).thenReturn(Collections.singletonList(UserDtoTestBuilder.builder().build().toUserDto()));

        mvc.perform(MockMvcRequestBuilders.get("/" + GetUserController.GET_USERS_URL)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]." + UserDto.ID_FIELD).exists())
                .andExpect(jsonPath("$[*]." + UserDto.ID_FIELD).value(UserDtoTestBuilder.ID_VALUE))
                .andExpect(jsonPath("$[*]." + UserDto.NAME_FIELD).value(UserDtoTestBuilder.NAME_VALUE))
                .andExpect(jsonPath("$[*]." + UserDto.EMAIL_FIELD).value(UserDtoTestBuilder.EMAIL_VALUE));
    }
}
