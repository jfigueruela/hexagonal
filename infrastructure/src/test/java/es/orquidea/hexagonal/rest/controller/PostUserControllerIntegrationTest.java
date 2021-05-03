package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.builder.UserDtoTestBuilder;
import es.orquidea.hexagonal.builder.UserTestBuilder;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PostUserController.class)
@AutoConfigureMockMvc
@EnableWebMvc
@Slf4j
public class PostUserControllerIntegrationTest {

    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    public void perform_post_user_201() throws Exception {

        //Given
        User user = UserTestBuilder.builder().build().toUser();
        when(userMapper.toDomain(any())).thenReturn(user);
        when(userService.saveUser(any())).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(UserDtoTestBuilder.builder().build().toUserDto());

        mvc.perform(post("/"+PostUserController.POST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserDtoTestBuilder.builder().build().toUserDtoJsonString())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(UserDtoTestBuilder.ID_VALUE))
                .andExpect(jsonPath("$.name").value(UserDtoTestBuilder.NAME_VALUE))
                .andExpect(jsonPath("$.email").value(UserDtoTestBuilder.EMAIL_VALUE));
    }

    @Test
    public void perform_post_user_400_error_no_name() throws Exception {
        mvc.perform(post("/"+PostUserController.POST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserDtoTestBuilder.builder().name(null).build().toUserDtoJsonString())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name is mandatory"));
    }

    @Test
    public void perform_post_user_400_error_no_email() throws Exception {
        mvc.perform(post("/"+PostUserController.POST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserDtoTestBuilder.builder().email(null).build().toUserDtoJsonString())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Email is mandatory"));
    }

    @Test
    public void perform_post_user_400_error_invalid_email() throws Exception {
        mvc.perform(post("/"+PostUserController.POST_USER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UserDtoTestBuilder.builder().email("xxxxxxx").build().toUserDtoJsonString())
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("must be a well-formed email address"));
    }

}
