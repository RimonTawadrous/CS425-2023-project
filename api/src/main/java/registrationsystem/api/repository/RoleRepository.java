package registrationsystem.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.Role;

import java.util.Optional;

public interface RoleRepository extends ListCrudRepository<Role, Long> {
    Optional<Role> findRoleByInternalName(String name);

}
