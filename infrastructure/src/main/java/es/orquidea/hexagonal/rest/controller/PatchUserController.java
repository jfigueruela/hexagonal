package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PatchUserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PatchMapping("users/user/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String id) {
        return new ResponseEntity<>(userMapper.toDto(userService.updateUser(id,userMapper.toDomain(userDto))),
                HttpStatus.OK);
    }
}
