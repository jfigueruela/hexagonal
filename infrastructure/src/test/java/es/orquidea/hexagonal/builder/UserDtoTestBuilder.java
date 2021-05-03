package es.orquidea.hexagonal.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import es.orquidea.hexagonal.rest.dto.UserDto;
import lombok.Builder;

@Builder
public class UserDtoTestBuilder {

    public static final String ID_VALUE ="test-id";
    public static final String NAME_VALUE ="test-name";
    public static final String EMAIL_VALUE ="test@email.com";

    @Builder.Default
    private String id = ID_VALUE;
    @Builder.Default
    private String name = NAME_VALUE;
    @Builder.Default
    private String email = EMAIL_VALUE;

    public UserDto toUserDto() {
        return UserDto.builder().id(id).name(name).email(email).build();
    }

    public String toUserDtoJsonString () {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String result = null;
        try {
            result = ow.writeValueAsString(toUserDto());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
