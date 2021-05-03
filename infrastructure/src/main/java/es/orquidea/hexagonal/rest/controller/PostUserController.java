package es.orquidea.hexagonal.rest.controller;

import es.orquidea.hexagonal.rest.dto.UserDto;
import es.orquidea.hexagonal.rest.mapper.UserMapper;
import es.orquidea.hexagonal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostUserController {

    public static final String POST_USER_URL = "user";

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(POST_USER_URL)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userMapper.toDto(userService.saveUser(userMapper.toDomain(userDto))),
                HttpStatus.CREATED);
    }

    //TODO: Look for a better place
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
