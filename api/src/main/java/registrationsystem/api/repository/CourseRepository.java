package registrationsystem.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import registrationsystem.api.model.Course;

public interface CourseRepository extends ListCrudRepository<Course, Long> {
}
