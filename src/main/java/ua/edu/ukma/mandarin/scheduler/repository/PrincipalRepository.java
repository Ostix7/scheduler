package ua.edu.ukma.mandarin.scheduler.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.security.Principal;

public interface PrincipalRepository extends CrudRepository<Principal, Long> {
  Optional<Principal> findByEmail(String email);
}
