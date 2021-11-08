package wolox.training.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wolox.training.models.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u", countQuery = "select count(u) from User u")
    Page<User> findAll(@Param(value = "page") Integer page, @Param("count") Integer countOfPages,
            @Param("sorting") String sorting, Pageable pageable);

    @Query(value = "SELECT u FROM User u where (u.birthday BETWEEN :fromDate AND :toDate) AND (:name is null or u.name = :name)")
    Optional<List<User>> findByBirthdayBetweenAndNameContaining(@Param(value = "fromDate") LocalDate fromDate,
            @Param(value = "toDate") LocalDate toDate, @Param(value = "name") String name);

}
