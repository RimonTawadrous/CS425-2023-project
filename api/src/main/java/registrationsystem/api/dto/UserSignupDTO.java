package registrationsystem.api.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserSignupDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
