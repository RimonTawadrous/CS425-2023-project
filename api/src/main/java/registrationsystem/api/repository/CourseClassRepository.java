package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.CourseClass;

public interface CourseClassRepository extends ListCrudRepository<CourseClass, Long> {
}
