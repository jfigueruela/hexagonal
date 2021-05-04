package es.orquidea.hexagonal.db.repository;

import es.orquidea.hexagonal.db.dbo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, String> {

}
