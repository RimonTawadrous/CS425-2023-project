package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.dto.request.ClassCreationDTO;
import registrationsystem.api.model.CourseClass;

@Service
public interface ClassService {
    public CourseClass create(ClassCreationDTO classCreationDTO) throws RecordNotFoundException;
    public CourseClass create(CourseClass courseClass);
    public CourseClass update(Long id,CourseClass courseClass)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
}
