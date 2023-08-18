package registrationsystem.api.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.repository.CourseClassRepository;
import registrationsystem.api.repository.StudentProfileRepository;
import registrationsystem.api.service.RegiestrationService;

@Service
public class RegiestrationServiceImpl implements RegiestrationService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CourseClassRepository courseClassRepository;

    @Override
    public Boolean register(Long studentId, Long courseId) throws RecordNotFoundException {
        var student = studentProfileRepository.findById(studentId).orElseThrow(()-> new RecordNotFoundException("Unable to register new course. Reason: student not found"));
        var course = courseClassRepository.findById(courseId).orElseThrow(()-> new RecordNotFoundException("Unable to register new course. Reason: Course not found"));
        return student.getCourseClasses().add(course);
    }

    @Override
    public Boolean drop(Long studentId, Long courseId) throws RecordNotFoundException {
        var student = studentProfileRepository.findById(studentId).orElseThrow(()-> new RecordNotFoundException("Unable to register new course. Reason: student not found"));
        var course = courseClassRepository.findById(courseId).orElseThrow(()-> new RecordNotFoundException("Unable to register new course. Reason: Course not found"));
        return student.getCourseClasses().remove(course);
    }
}
