package registrationsystem.api.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyCreationDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
