package es.orquidea.hexagonal.db.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
}