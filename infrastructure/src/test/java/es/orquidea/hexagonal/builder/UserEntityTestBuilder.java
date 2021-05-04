package es.orquidea.hexagonal.builder;

import es.orquidea.hexagonal.db.dbo.UserEntity;
import lombok.Builder;

import java.util.Optional;

@Builder
public class UserEntityTestBuilder {
    @Builder.Default
    private String id = "test-id";
    @Builder.Default
    private String name = "test-name";
    @Builder.Default
    private String email = "test-mail";

    public UserEntity toUserEntity() {
        return UserEntity.builder().id(id).name(name).email(email).build();
    }
    public Optional<UserEntity> toOptionaUserEntity() {
        return Optional.ofNullable(toUserEntity());
    }
}
