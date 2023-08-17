package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.StudentProfile;

@Service
public interface StudentProfileService {
    public StudentProfile create(StudentProfile studentProfile);

    public StudentProfile update (Long id, StudentProfile studentProfile) throws RecordNotFoundException;

    public void delete (Long id) throws RecordNotFoundException;
}
