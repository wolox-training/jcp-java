package wolox.training.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wolox.training.models.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE user_username=:userUsername", nativeQuery = true)
    Optional<User> findByUserUsername(@Param("userUsername") String username);
}
