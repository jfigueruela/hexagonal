package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.bootstrap.SpringBootService;
import es.orquidea.hexagonal.builder.UserDtoTestBuilder;
import es.orquidea.hexagonal.rest.dto.UserDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SpringBootService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserCRUDRealIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    @DisplayName("Create a new user and delete it")
    void post_user() {
        UserDto userDtoRequest = UserDtoTestBuilder.builder().build().toUserDto();

        UserDto userCreated = postTestBlock(userDtoRequest);
        checkResponseBodyValues(userDtoRequest, userCreated);

        deleteTestBlock(userCreated.getId());
    }

    @Test
    @Order(2)
    @DisplayName("Create a new user, retrieve new user created and delete it")
    void get_user() {
        UserDto userDtoRequest = UserDtoTestBuilder.builder().build().toUserDto();

        //Create a new user
        UserDto userCreated = postTestBlock(userDtoRequest);
        checkResponseBodyValues(userDtoRequest, userCreated);

        //Get user created
        UserDto userObtained = getByIdTestBlock(userCreated.getId());
        checkResponseBodyValues(userDtoRequest, userObtained);

        //Delete user created
        deleteTestBlock(userObtained.getId());
    }

    @Test
    @Order(3)
    @DisplayName("Create 2 users, get and delete them all")
    void get_users() {
        UserDto userDtoRequest = UserDtoTestBuilder.builder().build().toUserDto();

        //Create
        UserDto user1Created = postTestBlock(userDtoRequest);
        checkResponseBodyValues(userDtoRequest, user1Created);

        UserDto user2Created = postTestBlock(userDtoRequest);
        checkResponseBodyValues(userDtoRequest, user2Created);

        //Retrieve users
        List<UserDto> users = getTestBlock();
        assertEquals(2, users.size());
        users.forEach(userDto -> checkResponseBodyValues(userDtoRequest, userDto));

        //Delete
        users.forEach(userDto -> deleteTestBlock(userDto.getId()));

    }


    /**
     * Test disabled duen an error with PATCH request
     * Issue: https://github.com/jfigueruela/hexagonal/issues/9
     * Remove Disabled when issue fixed
     */
    @Disabled
    @Test
    @Order(4)
    @DisplayName("Create a new user, modify an attribute get user patched and delete it")
    void patch_user() {
        UserDto userDtoRequest = UserDtoTestBuilder.builder().build().toUserDto();

        //Create a new user
        UserDto userCreated = postTestBlock(userDtoRequest);
        checkResponseBodyValues(userDtoRequest, userCreated);

        //Update user
        UserDto userPatched =
                patchTestBlock(userCreated.getId(),
                        UserDtoTestBuilder.builder().name("Morty Smith").build().toUserDto());

        //Get user updated
        UserDto userObtained = getByIdTestBlock(userPatched.getId());
        assertEquals("Morty Smith", userObtained.getName());

        //Delete user
        deleteTestBlock(userObtained.getId());

    }


    private UserDto postTestBlock(UserDto userDtoRequest) {
        //Given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDtoRequest, headers);

        //When
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity("/" + PostUserController.POST_USER_URL,
                requestEntity, UserDto.class);

        //Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        return responseEntity.getBody();
    }

    private UserDto getByIdTestBlock(String userId) {
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity("/" + GetUserController.GET_USER_BY_ID_URL, UserDto.class, userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
    }


    private List<UserDto> getTestBlock() {
        return this.exchangeAsList("/" + GetUserController.GET_USERS_URL,
                new ParameterizedTypeReference<List<UserDto>>() {
                });
    }

    private UserDto patchTestBlock(String userId, UserDto userDtoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> requestEntity = new HttpEntity<>(userDtoRequest, headers);

        return restTemplate.exchange("/" + PatchUserController.PATCH_USER_URL, HttpMethod.PATCH, requestEntity,
                UserDto.class, userId).getBody();

    }

    private void deleteTestBlock(String userId) {
        //Delete User by Id
        ResponseEntity<UserDto> responseEntity;
        restTemplate.delete("/" + DeleteUserController.DELETE_USER_URL, userId);

        //Try to obtain user by it's id, but it's not found. That's ok.
        responseEntity = restTemplate.getForEntity("/" + GetUserController.GET_USER_BY_ID_URL, UserDto.class, userId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    private void checkResponseBodyValues(UserDto userDtoOrigin, UserDto userDtoDestination) {
        assertNotNull(userDtoDestination.getId());

        assertNotNull(userDtoDestination.getName());
        assertEquals(userDtoOrigin.getName(), userDtoDestination.getName());

        assertNotNull(userDtoDestination.getName());
        assertEquals(userDtoOrigin.getEmail(), userDtoDestination.getEmail());
    }

    private <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }

    private <T> T exchangeAs(HttpMethod method, String uri, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(uri, method, null, responseType).getBody();
    }
}
