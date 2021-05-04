package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GetUserController {

    public static final String GET_USER_BY_ID_URL = "users/user/{id}";
    public static final String GET_USERS_URL = "users";

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("users/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return new ResponseEntity<>(userMapper.toDto(userService.getUser(id)), HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userMapper.toDto(userService.getUsers()), HttpStatus.OK);
    }

}
