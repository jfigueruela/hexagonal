package es.orquidea.hexagonal.repository;

import es.orquidea.hexagonal.model.User;

import java.util.List;

public interface UserRepository {
    User findById(String id);
    List<User> getAll();
    User save(User user);
    void delete(User user);
}
