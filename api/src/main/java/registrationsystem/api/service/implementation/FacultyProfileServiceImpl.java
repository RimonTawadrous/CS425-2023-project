  package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.enums.RoleEnum;
import registrationsystem.api.model.FacultyProfile;
import registrationsystem.api.model.Role;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.FacultyProfileRepository;
import registrationsystem.api.repository.RoleRepository;
import registrationsystem.api.repository.StudentProfileRepository;
import registrationsystem.api.service.FacultyProfileService;
import registrationsystem.api.service.StudentProfileService;
import registrationsystem.api.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class FacultyProfileServiceImpl implements FacultyProfileService {

    private final FacultyProfileRepository repository;
    private final RoleRepository roleRepository;
    private final UserService userService;


    @Override
    @Transactional
    public FacultyProfile create(FacultyProfile facultyProfile) {
        Role facultyRole = roleRepository.findRoleByInternalName(RoleEnum.FACULTY.toString()).get();
        User user = facultyProfile.getUser();
        user.setRoles(List.of(facultyRole));

        User savedUser = userService.create(user);
        facultyProfile.setUser(savedUser);
        return repository.save(facultyProfile);
    }

    @Override
    public FacultyProfile update(Long id, FacultyProfile facultyProfile) throws RecordNotFoundException {
        FacultyProfile savedfacultyProfile = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Faculty not found"));
        User user = userService.update(savedfacultyProfile.getUser().getId(), savedfacultyProfile.getUser());
        facultyProfile.setId(savedfacultyProfile.getId());
        return repository.save(facultyProfile);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        FacultyProfile facultyProfile = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("Faculty not found"));
        userService.deactivate(facultyProfile.getUser());
    }
}