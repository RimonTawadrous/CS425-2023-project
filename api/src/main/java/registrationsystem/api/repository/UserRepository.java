package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.User;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String email);

}
