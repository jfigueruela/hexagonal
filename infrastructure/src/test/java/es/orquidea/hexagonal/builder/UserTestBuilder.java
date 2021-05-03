package es.orquidea.hexagonal.builder;

import es.orquidea.hexagonal.model.User;
import lombok.Builder;

@Builder
public class UserTestBuilder {
    @Builder.Default
    private String id = "test-id";
    @Builder.Default
    private String name = "test-name";
    @Builder.Default
    private String email = "test-mail";

    public User toUser() {
        return User.builder().id(id).name(name).email(email).build();
    }
}
