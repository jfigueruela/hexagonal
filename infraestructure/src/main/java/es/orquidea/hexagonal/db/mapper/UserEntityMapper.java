package es.orquidea.hexagonal.db.mapper;

import es.orquidea.hexagonal.db.dbo.UserEntity;
import es.orquidea.hexagonal.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User toDomain(UserEntity userEntity);
    UserEntity toDbo(User user);
    List<User> toDomain(List<UserEntity> userEntities);

}
