package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.enums.RoleEnum;
import registrationsystem.api.model.Role;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.RoleRepository;
import registrationsystem.api.repository.StudentProfileRepository;
import registrationsystem.api.service.StudentProfileService;
import registrationsystem.api.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository repository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public StudentProfile create(StudentProfile studentProfile){
        Role studentRole = roleRepository.findRoleByInternalName(RoleEnum.STUDENT.toString()).get();
        User user = studentProfile.getUser();
        user.setRoles(List.of(studentRole));

        User savedUser = userService.create(user);
        studentProfile.setUser(savedUser);
        System.out.println(savedUser);
        System.out.println(studentProfile);

        return repository.save(studentProfile);
    }

    public StudentProfile update (StudentProfile studentProfile){
        return repository.save(studentProfile);
    }

    public StudentProfile delete (Long id) throws RecordNotFoundException {
//        Optional<StudentProfile> studentProfile =
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("rersr"));
    }
}
