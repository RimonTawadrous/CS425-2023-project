package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.Course;

@Service
public interface CourseService
{
    public Course create(Course course);
    public Course update(Long id,Course course)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
}
