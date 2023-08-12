package registrationsystem.api.seeders;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import registrationsystem.api.enums.RoleEnum;
import registrationsystem.api.model.AdminProfile;
import registrationsystem.api.model.Role;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.RoleRepository;
import registrationsystem.api.service.AdminProfileService;
import registrationsystem.api.service.UserService;

import java.util.List;

@Component
@AllArgsConstructor
public class DatabaseSeeder {
    private final AdminProfileService adminProfileService;
    private JdbcTemplate jdbcTemplate;
    private RoleRepository roleRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedAdminsTable();

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

}
