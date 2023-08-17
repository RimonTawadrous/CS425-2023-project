package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.FacultyProfile;
import registrationsystem.api.model.User;

@Service
public interface FacultyProfileService {
    public FacultyProfile create(FacultyProfile facultyProfile);
    public FacultyProfile update(Long id,FacultyProfile facultyProfile)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
}
