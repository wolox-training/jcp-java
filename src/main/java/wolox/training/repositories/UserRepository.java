package wolox.training.repositories;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import wolox.training.models.Users;

public class UserRepository implements IUserRepository {

    @Autowired
    IUserRepository userRepository;

    @Override
    public <S extends Users> S save(S entity) {
        return userRepository.save(entity);
    }

    @Override
    public <S extends Users> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Users> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<Users> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Users entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Users> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
