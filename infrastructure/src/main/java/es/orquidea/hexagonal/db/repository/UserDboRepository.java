package es.orquidea.hexagonal.db.repository;

import es.orquidea.hexagonal.db.mapper.UserEntityMapper;
import es.orquidea.hexagonal.model.User;
import es.orquidea.hexagonal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
public class UserDboRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;
    private final UserEntityMapper userMapper;

    @Override
    public User findById(String id) {
        return userMapper.toDomain(springDataUserRepository.findById(id).orElse(null));
    }

    @Override
    public List<User> getAll() {
        return userMapper.toDomain(springDataUserRepository.findAll());
    }

    @Override
    public User save(User user) {
        return userMapper.toDomain(springDataUserRepository.save(userMapper.toDbo(user)));
    }

    @Override
    public void delete(User user) {
        springDataUserRepository.delete(userMapper.toDbo(user));
    }

}
