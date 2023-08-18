package registrationsystem.api.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.Course;
import registrationsystem.api.model.CourseClass;
import registrationsystem.api.repository.CourseClassRepository;
import registrationsystem.api.repository.CourseRepository;
import registrationsystem.api.service.ClassService;
import registrationsystem.api.service.CourseService;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Override
    public CourseClass create(CourseClass courseClass) {
        return courseClassRepository.save(courseClass);
    }

    @Override
    public CourseClass update(Long id, CourseClass courseClass) throws RecordNotFoundException {
        courseClassRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Unable to update Class, Class not found"));
        courseClass.setId(id);
        return courseClassRepository.save(courseClass);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        var courseToDelete = courseClassRepository.findById(id).orElseThrow(()-> new RecordNotFoundException("Unable to delete course, Course not found"));
        courseClassRepository.delete(courseToDelete);
    }
}
