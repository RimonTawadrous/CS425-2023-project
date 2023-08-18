package registrationsystem.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.Course;
import registrationsystem.api.service.CourseService;
import registrationsystem.api.service.RegiestrationService;

@RestController
@RequestMapping("/api/v1/course")
@AllArgsConstructor
public class CourseController {
    public final CourseService courseService;
    public final RegiestrationService regiestrationService;

    @PostMapping(value = "/new")
    public Course createCourse(@RequestBody Course course){
        return courseService.create(course);
    }

    @PutMapping(value = "/update/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course course) throws RecordNotFoundException {
        return courseService.update(courseId, course);
    }

    @PostMapping(value = "/delete/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) throws RecordNotFoundException {
        courseService.delete(courseId);
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
