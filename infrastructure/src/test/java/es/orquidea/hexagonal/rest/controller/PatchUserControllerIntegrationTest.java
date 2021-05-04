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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PatchUserController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class PatchUserControllerIntegrationTest {
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void perform_patch_user_201() throws Exception {
        User user = UserTestBuilder.builder().build().toUser();
        when(userMapper.toDomain(any())).thenReturn(user);
        when(userService.updateUser(any(),any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        mvc.perform(patch("/" + PatchUserController.PATCH_USER_URL,"test-id-path-param")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserDtoTestBuilder.builder().build().toUserDtoJsonString())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$." + UserDto.ID_FIELD).exists())
                .andExpect(jsonPath("$." + UserDto.ID_FIELD).value(UserDtoTestBuilder.ID_VALUE))
                .andExpect(jsonPath("$." + UserDto.NAME_FIELD).value(UserDtoTestBuilder.NAME_VALUE))
                .andExpect(jsonPath("$." + UserDto.EMAIL_FIELD).value(UserDtoTestBuilder.EMAIL_VALUE));
    }
}
