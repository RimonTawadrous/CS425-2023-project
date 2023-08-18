package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.CourseCreationDTO;
import registrationsystem.api.dto.response.CourseResponseDTO;
import registrationsystem.api.dto.response.FacultyProfileDTO;
import registrationsystem.api.model.Course;
import registrationsystem.api.model.FacultyProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.CourseRepository;
import registrationsystem.api.service.CourseService;
import registrationsystem.api.service.RegiestrationService;
import registrationsystem.api.utils.MappingUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {
    public final CourseService courseService;
    public final RegiestrationService regiestrationService;
    public final CourseRepository repository;
    private final ModelMapper modelMapper;
    private final MappingUtils mappingUtils;

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> index(){
        return ResponseEntity.ok(
                mappingUtils.mapList(repository.findAll(), CourseResponseDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> get(@PathVariable Long id)  throws RecordNotFoundException{
        Course course = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Course not found"));
        return ResponseEntity.ok(modelMapper.map(course,  CourseResponseDTO.class));

    }

    @PostMapping
    public Course createCourse(@RequestBody CourseCreationDTO courseCreationDTO){
        Course course = modelMapper.map(courseCreationDTO, Course.class);
        return courseService.create(course);
    }

    @PutMapping(value = "/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) throws RecordNotFoundException {
        return courseService.update(id, course);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCourse(@PathVariable Long id) throws RecordNotFoundException {
        courseService.delete(id);
    }

    @PostMapping(value = "/register/new/{studentId}/{courseId}")
    public Boolean register(@PathVariable Long studentId, @PathVariable Long  courseId) throws RecordNotFoundException {
        return regiestrationService.register(studentId, courseId);
    }

    @PostMapping(value = "/register/drop/{studentId}/{courseId}")
    public Boolean drop(@PathVariable Long studentId, @PathVariable Long  courseId) throws RecordNotFoundException {
        return regiestrationService.drop(studentId, courseId);
    }


}
