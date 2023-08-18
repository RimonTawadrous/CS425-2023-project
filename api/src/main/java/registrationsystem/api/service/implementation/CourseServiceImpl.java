package registrationsystem.api.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.Course;
import registrationsystem.api.repository.CourseClassRepository;
import registrationsystem.api.repository.CourseRepository;
import registrationsystem.api.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) throws RecordNotFoundException {
        courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Unable to update course, Course not found"));
        course.setId(id);
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        var courseToDelete = courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Unable to delete course, Course not found"));
        courseRepository.delete(courseToDelete);
    }
}
