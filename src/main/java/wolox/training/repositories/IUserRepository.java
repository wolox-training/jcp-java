package wolox.training.repositories;

import org.springframework.data.repository.CrudRepository;
import wolox.training.models.Users;

public interface IUserRepository extends CrudRepository<Users, Long> {

}
