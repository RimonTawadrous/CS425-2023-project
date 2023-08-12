package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.AdminProfile;

public interface AdminProfileRepository extends ListCrudRepository<AdminProfile, Long> {
}
