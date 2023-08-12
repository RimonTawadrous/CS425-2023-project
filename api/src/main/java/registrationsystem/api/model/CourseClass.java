package registrationsystem.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String buildingName;
    private String roomNumber;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private FacultyProfile facultyProfile;

    @ManyToOne()
    @JoinColumn(name="course_id")
    private Course course;

    @ManyToMany(mappedBy = "courseClasses")
    private List<StudentProfile> studentProfiles;
}
