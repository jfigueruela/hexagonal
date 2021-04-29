package es.orquidea.hexagonal.service;

import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
