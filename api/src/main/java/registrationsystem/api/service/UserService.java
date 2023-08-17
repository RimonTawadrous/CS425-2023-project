package registrationsystem.api.service;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.User;

public interface  UserService extends UserDetailsService, UserDetailsPasswordService {
    public User create(User user);
    public User update(Long id,User user)throws RecordNotFoundException;
    public void delete(Long id)throws RecordNotFoundException;
    public void deactivate(User user);
}
