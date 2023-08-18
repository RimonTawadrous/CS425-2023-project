package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.StudentProfile;
import registrationsystem.api.model.User;

@Service
public interface RegiestrationService {
    Boolean register (Long studentId, Long courseId) throws RecordNotFoundException;
    Boolean drop (Long studentId, Long courseId) throws RecordNotFoundException;

}
