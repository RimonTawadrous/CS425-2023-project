package registrationsystem.api.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import registrationsystem.api.common.exception.RecordNotFoundException;
import registrationsystem.api.model.StudentProfile;
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
    public User update(Long id,User user)throws RecordNotFoundException {
        User savedUser = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("User not found"));
        String hashedPassString = passwordEncoder.encode(user.getPassword());
        savedUser.setPassword(hashedPassString);
        savedUser.setEmail(user.getEmail());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setMiddleName(user.getMiddleName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPassword(user.getPassword());
        user.setId(savedUser.getId());
        user.setActive(true);
        return repository.save(user);
    }

    @Override
    public void delete(Long id) throws RecordNotFoundException {
        User user = repository.findById(id).orElseThrow(()-> new RecordNotFoundException("User not found"));
        user.setActive(false);
        repository.delete(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s is not found", username)));
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void deactivate(User user) {
        user.setActive(false);
        repository.save(user);
    }


}
