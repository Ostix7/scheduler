package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("Select user from User user")
    List<User> findAll();

    Optional<User> findByEmail(String email);

    User findOneById(int id);

    User findOneByEmail(String email);
    User findUserByEmail(String email);

    Optional<User> findById(Long id);


}
