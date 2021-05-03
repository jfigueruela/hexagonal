package es.orquidea.hexagonal.rest.mapper;

import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.rest.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring",imports = {UUID.class})
public interface UserMapper {

    User toDomain(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDto(List<User> users);
}
