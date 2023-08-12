package registrationsystem.api.dto.request;

import lombok.Data;
import registrationsystem.api.dto.UserSignupDTO;

import java.time.LocalDate;

@Data
public class StudentCreationDTO {
    private long studentNumber;
    private float cgpa;
    private LocalDate dateOfEnrollment;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
