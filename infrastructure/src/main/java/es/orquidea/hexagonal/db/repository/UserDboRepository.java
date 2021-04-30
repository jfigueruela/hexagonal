package es.orquidea.hexagonal.db.repository;

import es.orquidea.hexagonal.db.mapper.UserEntityMapper;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserDboRepository implements UserRepository {

    private final SpringDataUserRepository userRepository;
    private final UserEntityMapper userMapper;

    @Override
    public User findById(Long id) {
        return userMapper.toDomain(userRepository.findById(id).get());
    }

    @Override
    public List<User> getAll() {
         return userMapper.toDomain(userRepository.findAll());
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(userRepository.save(userMapper.toDbo(user)));
    }

    @Override
    public void delete(User user) {
        userRepository.delete(userMapper.toDbo(user));
    }
}
