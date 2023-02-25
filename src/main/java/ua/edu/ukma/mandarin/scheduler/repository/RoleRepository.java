package ua.edu.ukma.mandarin.scheduler.repository;

import org.springframework.data.repository.CrudRepository;
import ua.edu.ukma.mandarin.scheduler.domain.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {}
