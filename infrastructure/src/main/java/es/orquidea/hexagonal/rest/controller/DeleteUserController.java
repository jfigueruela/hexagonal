package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteUserController {
    private final UserService userService;
    @DeleteMapping("users/user/{id}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
