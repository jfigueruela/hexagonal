package es.orquidea.hexagonal.db.dbo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    private Long id;

    private String name;

    private String address;

}