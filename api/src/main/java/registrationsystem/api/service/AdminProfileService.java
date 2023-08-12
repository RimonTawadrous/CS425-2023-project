package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.model.AdminProfile;

@Service
public interface AdminProfileService {

    public AdminProfile create(AdminProfile admin);
}
