package registrationsystem.api.service;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import registrationsystem.api.model.User;

public interface  UserService extends UserDetailsService, UserDetailsPasswordService {
    public User create(User user);
}
