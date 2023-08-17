package registrationsystem.api.service;

import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;

@Service
public interface ClassService {
    public Class create(Class classObj);
    public Class update(Long id,Class classObj)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
}
