package es.orquidea.hexagonal.db.dbo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    private String id;
    private String name;
    private String email;
}