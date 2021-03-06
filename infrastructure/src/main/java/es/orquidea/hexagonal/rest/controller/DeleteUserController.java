package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteUserController {

    public static final String DELETE_USER_URL = "users/user/{id}";

    private final UserService userService;

    @DeleteMapping(DELETE_USER_URL)
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
