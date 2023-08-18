package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public StudentProfile create(StudentProfile studentProfile){
        Role studentRole = roleRepository.findRoleByInternalName(RoleEnum.STUDENT.toString()).get();
        User user = studentProfile.getUser();
        user.setActive(true);
        user.setRoles(List.of(studentRole));
        User savedUser = userService.create(user);
        studentProfile.setUser(savedUser);
        return repository.save(studentProfile);
    }

    public StudentProfile update (Long id ,StudentProfile studentProfile) throws RecordNotFoundException{
        StudentProfile savedStudent = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Student not found"));
        User user = userService.update(savedStudent.getUser().getId(), studentProfile.getUser());
        studentProfile.setId(savedStudent.getId());
        studentProfile.setUser(user);
//        studentProfile.setUser();
        return repository.save(studentProfile);
    }

    public void delete (Long id) throws RecordNotFoundException {
        StudentProfile studentProfile = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Student not found"));
        userService.deactivate(studentProfile.getUser());
    }
}
