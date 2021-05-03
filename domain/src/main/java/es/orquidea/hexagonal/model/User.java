package es.orquidea.hexagonal.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
public class User {
    private String id;
    private String name;
    private String email;
}
