package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.AdminProfile;

@Service
public interface AdminProfileService {

    public AdminProfile create(AdminProfile admin);
    public AdminProfile update(Long id,AdminProfile admin)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
}
