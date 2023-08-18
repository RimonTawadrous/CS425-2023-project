package registrationsystem.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private long studentNumber;

    private float cgpa;

//    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfEnrollment;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "students_course_classes"
            , joinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "ID")}
            , inverseJoinColumns = {@JoinColumn(name = "course_class_id", referencedColumnName = "ID")})
    private List<CourseClass> courseClasses;



    @Override
    public String toString() {
        return "StudentProfile{" +
                "id=" + id +
                ", studentNumber=" + studentNumber +
                ", cgpa=" + cgpa +
                ", dateOfEnrollment=" + dateOfEnrollment +
                ", user=" + user +
                ", courseClasses=" + courseClasses +
                '}';
    }
}
