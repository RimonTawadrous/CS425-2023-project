package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.FacultyProfile;

public interface FacultyProfileRepository extends ListCrudRepository<FacultyProfile, Long> {
}
