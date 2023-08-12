package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import registrationsystem.api.model.User;
import registrationsystem.api.repository.UserRepository;
import registrationsystem.api.service.UserService;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user)
    {
        String hashedPassString = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassString);
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s is not found", username)));
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }
}
