package registrationsystem.api.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.ClassCreationDTO;
import registrationsystem.api.model.Course;
import registrationsystem.api.model.CourseClass;
import registrationsystem.api.model.FacultyProfile;
import registrationsystem.api.repository.CourseClassRepository;
import registrationsystem.api.repository.CourseRepository;
import registrationsystem.api.repository.FacultyProfileRepository;
import registrationsystem.api.service.ClassService;
import registrationsystem.api.service.CourseService;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private CourseClassRepository courseClassRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private FacultyProfileRepository facultyProfileRepository;
    @Override
    public CourseClass create(ClassCreationDTO classCreationDTO) throws RecordNotFoundException {
        Course course = courseRepository.findById(classCreationDTO.getCourseId()).orElseThrow(()-> new RecordNotFoundException("Unable to update Class, Class not found"));
        FacultyProfile facultyProfile = facultyProfileRepository.findById(classCreationDTO.getFacultyId()).orElseThrow(()-> new RecordNotFoundException("Unable to update Class, Class not found"));
        CourseClass courseClass = CourseClass.builder()
                .buildingName(classCreationDTO.getBuildingName())
                .roomNumber(classCreationDTO.getRoomNumber())
                .course(course)
                .facultyProfile(facultyProfile)
                .build();
        return courseClassRepository.save(courseClass);
    }


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
