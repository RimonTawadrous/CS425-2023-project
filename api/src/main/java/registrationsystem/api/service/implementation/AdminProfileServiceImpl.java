package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import registrationsystem.api.enums.RoleEnum;
import registrationsystem.api.model.AdminProfile;
import registrationsystem.api.model.Role;
import registrationsystem.api.repository.AdminProfileRepository;
import registrationsystem.api.repository.RoleRepository;
import registrationsystem.api.service.AdminProfileService;
import registrationsystem.api.service.UserService;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminProfileServiceImpl implements AdminProfileService {

    private final AdminProfileRepository repository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Override
    public AdminProfile create(AdminProfile admin)
    {
        Role adminRole = roleRepository.findRoleByInternalName(RoleEnum.ADMIN.toString()).get();
        admin.getUser().setRoles(List.of(adminRole));
        userService.create(admin.getUser());
        return repository.save(admin);
    }
}
