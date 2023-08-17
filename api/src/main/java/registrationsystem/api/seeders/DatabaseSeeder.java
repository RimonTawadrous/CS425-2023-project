package registrationsystem.api.seeders;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import registrationsystem.api.enums.RoleEnum;
import registrationsystem.api.model.*;
import registrationsystem.api.repository.RoleRepository;
import registrationsystem.api.service.AdminProfileService;
import registrationsystem.api.service.FacultyProfileService;
import registrationsystem.api.service.StudentProfileService;
import registrationsystem.api.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseSeeder {
    private final AdminProfileService adminProfileService;
    private final StudentProfileService studentProfileService;
    private final FacultyProfileService facultyProfileService;

    private JdbcTemplate jdbcTemplate;
    private RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedAdminsTable();
        seedFacultiesTable();
        seedStudentsTable();
    }

    private void seedRoleTable(){

        if(roleRepository.findRoleByInternalName(RoleEnum.ADMIN.toString()).isEmpty()){
            Role adminRole = Role.builder().internalName(RoleEnum.ADMIN.toString()).build();
            roleRepository.save(adminRole);
        }
        if(roleRepository.findRoleByInternalName(RoleEnum.STUDENT.toString()).isEmpty()){
            Role studentRole = Role.builder().internalName(RoleEnum.STUDENT.toString()).build();
            roleRepository.save(studentRole);
        }
        if(roleRepository.findRoleByInternalName(RoleEnum.FACULTY.toString()).isEmpty()){
            Role facultyRole = Role.builder().internalName(RoleEnum.FACULTY.toString()).build();
            roleRepository.save(facultyRole);
        }
    }

    private void seedAdminsTable(){
        String sql = "SELECT * FROM  admin_profile LIMIT 1";
        List<AdminProfile> admins = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(!admins.isEmpty()) {
            return;
        }
        User user = User.builder()
                .email("email@email.com")
                .username("admin")
                .password("123")
                .firstName("Admin")
                .lastName("1")
                .build();

        AdminProfile admin = new AdminProfile();
        admin.setUser(user);
        adminProfileService.create(admin);
    }

    private void seedFacultiesTable(){
        String sql = "SELECT * FROM  faculty_profile LIMIT 1";
        List<AdminProfile> faculties = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(!faculties.isEmpty()) {
            return;
        }
        User user = User.builder()
                .email("faculty@email.com")
                .username("faculty")
                .password("123")
                .firstName("faculty")
                .lastName("1")
                .build();

        FacultyProfile faculty = new FacultyProfile();
        faculty.setUser(user);
        facultyProfileService.create(faculty);
    }

    private void seedStudentsTable(){
        String sql = "SELECT * FROM  student_profile LIMIT 1";
        List<AdminProfile> students = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if(!students.isEmpty()) {
            return;
        }
        User user = User.builder()
                .email("student@email.com")
                .username("student")
                .password("123")
                .firstName("Student")
                .lastName("1")
                .build();

        StudentProfile student = new StudentProfile();
        student.setUser(user);
        student.setStudentNumber(1234);
        student.setCgpa(4);
        student.setDateOfEnrollment(LocalDate.now());
        studentProfileService.create(student);
    }

}
