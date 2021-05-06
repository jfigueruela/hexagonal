package es.orquidea.hexagonal.db.repository;

import es.orquidea.hexagonal.db.dbo.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataUserRepository extends MongoRepository<UserEntity, String> {

}
