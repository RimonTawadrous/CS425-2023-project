package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.StudentProfile;

public interface StudentProfileRepository extends ListCrudRepository<StudentProfile, Long> {
}
