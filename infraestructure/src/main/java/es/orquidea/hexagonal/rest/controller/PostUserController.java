package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.saveUser(userMapper.toDomain(userDto))),
                HttpStatus.CREATED);
    }

}
