package es.orquidea.hexagonal.service;

import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUser(Long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return user;
    }

    public List<User> getUsers() {
        return userRepository.getAll();
    }

    public User saveUser(User user) {
        log.debug("Storing User [{}]", user);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.delete(getUser(id));
    }
}
