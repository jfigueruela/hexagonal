package es.orquidea.hexagonal.repository;

import es.orquidea.hexagonal.model.User;

import java.util.List;

public interface UserRepository {
    //Get
    User findById(Long id);
    List<User> getAll();
    //Post
    User save(User user);
}
